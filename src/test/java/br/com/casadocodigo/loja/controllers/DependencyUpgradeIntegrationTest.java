package br.com.casadocodigo.loja.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class,
        DataSourceConfigurationTest.class, SecurityConfiguration.class})
@ActiveProfiles("test")
public class DependencyUpgradeIntegrationTest {

    @Autowired private WebApplicationContext context;
    @Autowired private ProdutoDAO produtos;
    @Autowired private UsuarioDAO usuarios;
    @Autowired private RoleDAO roles;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void administradorPodeAbrirFormulario() throws Exception {
        mvc.perform(get("/produtos/form").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/produtos/form.jsp"));
    }

    @Test
    public void gravacaoContinuaExigindoCsrf() throws Exception {
        mvc.perform(post("/produtos").with(user("admin").roles("ADMIN")))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    public void loginAutenticaUsuarioPersistidoComBCrypt() throws Exception {
        Role role = new Role("ROLE_ADMIN");
        roles.gravar(role);
        Usuario usuario = new Usuario();
        usuario.setEmail("migration-test@example.invalid");
        usuario.setNome("Teste de compatibilidade");
        usuario.setSenha(new BCryptPasswordEncoder().encode("test-password"));
        usuario.setRoles(Arrays.asList(role));
        usuarios.gravar(usuario);

        mvc.perform(formLogin().user(usuario.getEmail()).password("test-password"))
                .andExpect(authenticated().withUsername(usuario.getEmail()));
    }

    @Test
    @Transactional
    public void relatorioPreservaFiltroEContratoJsonComHibernateAtualizado() throws Exception {
        Produto antigo = ProdutoBuilder.newProduto().buildOne();
        antigo.setDataLancamento(new GregorianCalendar(2019, 0, 1));
        produtos.gravar(antigo);
        Produto recente = ProdutoBuilder.newProduto().buildOne();
        recente.setTitulo("Livro recente");
        recente.setDataLancamento(new GregorianCalendar(2024, 0, 1));
        produtos.gravar(recente);

        String body = mvc.perform(get("/relatorio-produtos").param("data", "2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        JsonNode json = new ObjectMapper().readTree(body);
        assertEquals(1, json.get("quantidade").asInt());
        JsonNode produto = json.get("produtos").get(0);
        assertEquals("Livro recente", produto.get("titulo").asText());
        assertEquals("COMBO", produto.get("preco").get(0).get("tipo").asText());
        assertEquals(10, produto.get("preco").get(0).get("valor").asInt());
    }
}

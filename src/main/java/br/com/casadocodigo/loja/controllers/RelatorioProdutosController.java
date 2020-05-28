package br.com.casadocodigo.loja.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.controllers.dto.RelatorioProdutosDto;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.util.DataUtil;

@RestController
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO dao;
	
	
	
	@GetMapping("relatorio-produtos")
	@ResponseBody
	public void relatorioProdutos(@PathVariable(name = "data" , required = false) String data ) {
		
		Date dtPesquisa = null;
		try {
			dtPesquisa = DataUtil.stringToDate(data);
		}catch (Exception e) {
			return ;
		}
		 
		List <Produto> listaProdutos =dao.obterLancamentosApartirDe(  dtPesquisa   );
		RelatorioProdutosDto relatorioProduto = new RelatorioProdutosDto( dtPesquisa, listaProdutos );
		
	}
	
}

package br.com.casadocodigo.loja;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioDto {
	
	public UsuarioDto() {}
	
	public UsuarioDto(Usuario u) {
		this.email =u.getEmail();
		this.nome = u.getNome();
		
	}
	
	
	private String email;
	private String nome;
	private String senha;
	private String reSenha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getReSenha() {
		return reSenha;
	}
	public void setReSenha(String reSenha) {
		this.reSenha = reSenha;
	}
	
	
	
}

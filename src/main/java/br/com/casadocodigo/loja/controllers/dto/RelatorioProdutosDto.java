package br.com.casadocodigo.loja.controllers.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import br.com.casadocodigo.loja.models.Produto;

public class RelatorioProdutosDto {
	
	public RelatorioProdutosDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RelatorioProdutosDto(Calendar dataGeracao, Collection<Produto> produtos) {
		super();
		
		
		this.dataGeracao = dataGeracao.getTimeInMillis();
		this.quantidade = produtos.size();
		this.produtos = new ArrayList<ProdutoDto>();
		produtos.forEach(p ->{
			this.produtos.add(new ProdutoDto(p));
		});
		
		
	}


	private long dataGeracao;
	private int quantidade;
	private Collection<ProdutoDto> produtos;
	public long getDataGeracao() {
		return dataGeracao;
	}


	public void setDataGeracao(long dataGeracao) {
		this.dataGeracao = dataGeracao;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public Collection<ProdutoDto> getProdutos() {
		return produtos;
	}


	public void setProdutos(Collection<ProdutoDto> produtos) {
		this.produtos = produtos;
	}
	
	
	
}

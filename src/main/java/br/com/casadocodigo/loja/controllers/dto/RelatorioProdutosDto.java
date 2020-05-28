package br.com.casadocodigo.loja.controllers.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.casadocodigo.loja.models.Produto;

public class RelatorioProdutosDto {
	
	public RelatorioProdutosDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RelatorioProdutosDto(Date dataGeracao, Collection<Produto> produtos) {
		super();
		
		
		this.dataGeracao = dataGeracao.getTime();
		this.quantidade = produtos.size();
		this.produtos = new ArrayList<ProdutoDto>();
		produtos.forEach(p ->{
			this.produtos.add(new ProdutoDto(p));
		});
		
		
	}


	private long dataGeracao;
	private int quantidade;
	private Collection<ProdutoDto> produtos;
}

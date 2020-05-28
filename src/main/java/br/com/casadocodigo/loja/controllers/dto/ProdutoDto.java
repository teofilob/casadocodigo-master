package br.com.casadocodigo.loja.controllers.dto;

import java.util.ArrayList;
import java.util.Collection;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoDto {
	
	public ProdutoDto( Produto p  ) {
		this.id = p.getId();
		this.titulo = p.getTitulo();
		this.descricao = p.getDescricao();
		this.paginas = p.getPaginas();
		this.sumarioPath = p.getSumarioPath();
		this.precos = new ArrayList<PrecoDto>();
		p.getPrecos().forEach( p1 ->{
			precos.add(  new PrecoDto(p1)  );
		} );
		this.dataLancamento = p.getDataLancamento().getTimeInMillis();
				
	}
	
	
	private long id;
	private String titulo;
	private String descricao;
	private int paginas;
	private String sumarioPath;
	private Collection<PrecoDto> precos;
	private long dataLancamento;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String getSumarioPath() {
		return sumarioPath;
	}
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	public Collection<PrecoDto> getPreco() {
		return precos;
	}
	public void setPreco(Collection<PrecoDto> preco) {
		this.precos = precos;
	}
	public long getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(long dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	
}

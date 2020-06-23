package br.com.casadocodigo.loja.controllers.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoMinDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3847562427356346774L;
	/**
	 * 
	 */
	
	public ProdutoMinDto() {}
	public ProdutoMinDto( Produto p  ) {
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
	
	private String titulo;
	private String descricao;
	private int paginas;
	private String sumarioPath;
	private Collection<PrecoDto> precos = new ArrayList<PrecoDto>();
	private long dataLancamento;
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
	public Collection<PrecoDto> getPrecos() {
		return precos;
	}
	public void setPrecos(Collection<PrecoDto> precos) {
		this.precos = precos;
	}
	public long getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(long dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
}

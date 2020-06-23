package br.com.casadocodigo.loja.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.casadocodigo.util.DataUtil;

public class PedidoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8522546455992008714L;

	public PedidoDto () {
		this.produtos = new ArrayList<ProdutoMinDto>();
	}
	
	private long id;
	private BigDecimal valor;
	private long data;
	private Collection<ProdutoMinDto> produtos = new ArrayList<ProdutoMinDto>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public Collection<ProdutoMinDto> getProdutos() {
		return produtos;
	}
	public void setProdutos(Collection<ProdutoMinDto> produtos) {
		this.produtos = produtos;
	}
	
	public String getDataFormat() {
		return DataUtil.dataToString(new Date(this.data));
	}
	
	
}

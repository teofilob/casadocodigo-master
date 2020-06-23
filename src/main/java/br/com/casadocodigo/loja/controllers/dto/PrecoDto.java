package br.com.casadocodigo.loja.controllers.dto;

import java.math.BigDecimal;

import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.TipoPreco;

public class PrecoDto {
	
	public PrecoDto() {}
	
	public PrecoDto( Preco p ) {
		this.tipo = p.getTipo();
		this.valor = p.getValor();
	}
	
	private BigDecimal valor;
	private TipoPreco tipo;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	
}

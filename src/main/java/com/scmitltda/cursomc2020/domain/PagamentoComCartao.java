package com.scmitltda.cursomc2020.domain;

import javax.persistence.Entity;

import com.scmitltda.cursomc2020.domain.enuns.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer pagamentoDeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, 
			Pedido pedido, Integer pagamentoDeParcelas) {
		super(id, estadoPagamento, pedido);
		
		this.pagamentoDeParcelas = pagamentoDeParcelas;
	}

	public Integer getPagamentoDeParcelas() {
		return pagamentoDeParcelas;
	}

	public void setPagamentoDeParcelas(Integer pagamentoDeParcelas) {
		this.pagamentoDeParcelas = pagamentoDeParcelas;
	}
}

package com.luan.invoicetracker.enums;

public enum VerificacaoTipo {

	CONTA("CONTA"),
	SENHA("SENHA");
	
	private final String tipo;
	
	VerificacaoTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo.toLowerCase();
	}
	
}

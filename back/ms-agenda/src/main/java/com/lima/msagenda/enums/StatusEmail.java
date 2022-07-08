package com.lima.msagenda.enums;

public enum StatusEmail {

	ENVIADO(1, "Enviado"), ERRO(2, "Erro");

	private int cod;
	private String descricao;

	private StatusEmail(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEmail toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusEmail x : StatusEmail.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

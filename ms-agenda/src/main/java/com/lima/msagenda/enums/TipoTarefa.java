package com.lima.msagenda.enums;

public enum TipoTarefa {

	FAXINA(1, "Faxina"), COMIDA(2, "Comida");

	private int cod;
	private String descricao;

	private TipoTarefa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoTarefa toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoTarefa x : TipoTarefa.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

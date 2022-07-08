package com.lima.msagenda.enums;

public enum DiaSemana {

	DOMINGO(1, "Domingo"), SEGUNDA(2, "Segunda-Feira"), TERCA(3, "Terça-Feira"), QUARTA(4, "Quarta-Feira"),
	QUINTA(5, "Quinta-Feira"), SEXTA(6, "Sexta-Feira"), SABADO(7, "Sábado");

	private int cod;
	private String descricao;

	private DiaSemana(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static DiaSemana toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (DiaSemana x : DiaSemana.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

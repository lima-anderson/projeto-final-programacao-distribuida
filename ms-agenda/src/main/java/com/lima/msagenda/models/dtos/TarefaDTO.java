package com.lima.msagenda.models.dtos;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class TarefaDTO {

	private Long id;
	private Integer tipo;
	private Boolean status;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data = LocalDateTime.now();

	private Long usuario;

	public TarefaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

}

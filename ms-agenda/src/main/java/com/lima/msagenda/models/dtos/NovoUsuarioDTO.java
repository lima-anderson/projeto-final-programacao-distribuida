package com.lima.msagenda.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lima.msagenda.services.validation.UsuarioUpdate;

//@UsuarioUpdate
public class NovoUsuarioDTO {

	private Long id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String password;
	private String curso;
	private Integer diaDeCozinhar;
	private Integer diaDeLimpar;

	public NovoUsuarioDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Integer getDiaDeCozinhar() {
		return diaDeCozinhar;
	}

	public void setDiaDeCozinhar(Integer diaDeCozinhar) {
		this.diaDeCozinhar = diaDeCozinhar;
	}

	public Integer getDiaDeLimpar() {
		return diaDeLimpar;
	}

	public void setDiaDeLimpar(Integer diaDeLimpar) {
		this.diaDeLimpar = diaDeLimpar;
	}

}

package com.lima.msagenda.models.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lima.msagenda.models.Tarefa;
import com.lima.msagenda.models.Usuario;

//@UsuarioInsert
public class UsuarioDTO {

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

	private List<Tarefa> tarefas;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		password = user.getPassword();
		curso = user.getCurso();
		tarefas = user.getTarefas();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
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

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}

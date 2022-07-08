package com.lima.msagenda.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lima.msagenda.enums.DiaSemana;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private String curso;

	private Integer diaDeCozinhar;

	private Integer diaDeLimpar;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Tarefa> tarefas;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();

	public Usuario() {
	}

	public Usuario(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Usuario(String name, String email, String password, String curso, DiaSemana diaDeCozinhar,
			DiaSemana diaDeLimpar) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.curso = curso;
		this.diaDeCozinhar = (diaDeCozinhar == null) ? null : diaDeCozinhar.getCod();
		this.diaDeLimpar = (diaDeLimpar == null) ? null : diaDeLimpar.getCod();
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

//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	public DiaSemana getDiaDeCozinhar() {
		return DiaSemana.toEnum(diaDeCozinhar);
	}

	public void setDiaDeCozinhar(DiaSemana diaDeCozinhar) {
		this.diaDeCozinhar = diaDeCozinhar.getCod();
	}

	public DiaSemana getDiaDeLimpar() {
		return DiaSemana.toEnum(diaDeLimpar);
	}

	public void setDiaDeLimpar(DiaSemana diaDeLimpar) {
		this.diaDeLimpar = diaDeLimpar.getCod();
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Tarefa tarefa) {
		tarefas.add(tarefa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

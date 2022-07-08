package com.pdist.msemail.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pdist.msemail.enums.StatusEmail;

@Table(name = "EMAIL")
@Entity
public class EmailModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String proprietarioReferencia;
	private String remetente;
	private String destinatario;
	private String tituloDoEmail;

	@Column(columnDefinition = "TEXT")
	private String corpoDoEmail;

	private LocalDateTime dataDeEnvio = LocalDateTime.now();
	private StatusEmail status;

	public EmailModel() {
	}

	public EmailModel(String proprietarioReferencia, String remetente, String destinatario, String tituloDoEmail,
			String corpoDoEmail) {

		this.proprietarioReferencia = proprietarioReferencia;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.tituloDoEmail = tituloDoEmail;
		this.corpoDoEmail = corpoDoEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProprietarioReferencia() {
		return proprietarioReferencia;
	}

	public void setProprietarioReferencia(String proprietarioReferencia) {
		this.proprietarioReferencia = proprietarioReferencia;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTituloDoEmail() {
		return tituloDoEmail;
	}

	public void setTituloDoEmail(String tituloDoEmail) {
		this.tituloDoEmail = tituloDoEmail;
	}

	public String getCorpoDoEmail() {
		return corpoDoEmail;
	}

	public void setCorpoDoEmail(String corpoDoEmail) {
		this.corpoDoEmail = corpoDoEmail;
	}

	public LocalDateTime getDataDeEnvio() {
		return dataDeEnvio;
	}

	public void setDataDeEnvio(LocalDateTime dataDeEnvio) {
		this.dataDeEnvio = dataDeEnvio;
	}

	public StatusEmail getStatus() {
		return status;
	}

	public void setStatus(StatusEmail status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailModel other = (EmailModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package com.pdist.msemail.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.pdist.msemail.models.EmailModel;

public class EmailDto {

	@NotBlank
	private String proprietarioReferencia;

	@NotBlank
	@Email
	private String remetente;

	@NotBlank
	@Email
	private String destinatario;

	@NotBlank
	private String tituloDoEmail;

	@NotBlank
	private String corpoDoEmail;

	public EmailDto() {
	}

	public EmailDto(@NotBlank String proprietarioReferencia, @NotBlank @Email String remetente,
			@NotBlank @Email String destinatario, @NotBlank String tituloDoEmail, @NotBlank String corpoDoEmail) {
		this.proprietarioReferencia = proprietarioReferencia;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.tituloDoEmail = tituloDoEmail;
		this.corpoDoEmail = corpoDoEmail;
	}

	public EmailDto(EmailModel obj) {
		this.proprietarioReferencia = obj.getProprietarioReferencia();
		this.remetente = obj.getRemetente();
		this.destinatario = obj.getDestinatario();
		this.tituloDoEmail = obj.getTituloDoEmail();
		this.corpoDoEmail = obj.getCorpoDoEmail();
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

	public EmailModel DeEmailDto() {
		return new EmailModel(proprietarioReferencia, remetente, destinatario, tituloDoEmail, corpoDoEmail);
	}

}

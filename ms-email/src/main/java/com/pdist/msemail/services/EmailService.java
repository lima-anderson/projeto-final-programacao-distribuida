package com.pdist.msemail.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdist.msemail.enums.StatusEmail;
import com.pdist.msemail.models.EmailModel;
import com.pdist.msemail.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository repo;

	@SuppressWarnings("finally")
	public EmailModel enviarEmail(EmailModel email) {

		String emailAdmin = "";
		String senhaAdmin = "";

		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setHostName("smtp.gmail.com");
		simpleEmail.setSmtpPort(465);
		simpleEmail.setAuthenticator(new DefaultAuthenticator(emailAdmin, senhaAdmin));
		simpleEmail.setSSLOnConnect(true);

		try {
			simpleEmail.setFrom(email.getRemetente());
			simpleEmail.addTo(email.getDestinatario());
			simpleEmail.setSubject(email.getTituloDoEmail());
			simpleEmail.setMsg(email.getCorpoDoEmail());

			simpleEmail.send();
			email.setStatus(StatusEmail.ENVIADO);

			System.out.println("Enviado");
		} catch (EmailException e) {
			email.setStatus(StatusEmail.ERRO);
			System.out.println("Erro");
		} finally {
			System.out.println("Passou");
			return repo.save(email);
		}
	}
}

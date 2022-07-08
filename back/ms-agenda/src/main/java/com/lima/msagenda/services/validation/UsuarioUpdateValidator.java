package com.lima.msagenda.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.lima.msagenda.controllers.exceptions.FieldMessage;
import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.NovoUsuarioDTO;
import com.lima.msagenda.repositories.UsuarioRepository;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, NovoUsuarioDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioUpdate ann) {
	}

	@Override
	public boolean isValid(NovoUsuarioDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existenten"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
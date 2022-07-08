package com.lima.msagenda.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lima.msagenda.controllers.exceptions.FieldMessage;
import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.UsuarioDTO;
import com.lima.msagenda.repositories.UsuarioRepository;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = repo.findByEmail(objDto.getEmail());

		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
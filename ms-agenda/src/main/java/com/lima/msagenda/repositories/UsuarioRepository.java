package com.lima.msagenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lima.msagenda.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

}

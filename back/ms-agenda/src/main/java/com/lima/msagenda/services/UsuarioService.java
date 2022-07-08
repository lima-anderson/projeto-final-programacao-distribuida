package com.lima.msagenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lima.msagenda.enums.DiaSemana;
import com.lima.msagenda.feingClients.ExpedidorEmailFeingCLeint;
import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.EmailDto;
import com.lima.msagenda.models.dtos.NovoUsuarioDTO;
import com.lima.msagenda.models.dtos.UsuarioDTO;
import com.lima.msagenda.repositories.RoleRepository;
import com.lima.msagenda.repositories.TarefaRepository;
import com.lima.msagenda.repositories.UsuarioRepository;
import com.lima.msagenda.services.exceptions.DataIntegrityException;
import com.lima.msagenda.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private ExpedidorEmailFeingCLeint expedidorEmailFeingCLeint;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario buscarPorEmail(String email) {
		Usuario obj = usuarioRepository.findByEmail(email);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"E-mail não encontrado! E-mail: " + email + ", Tipo: " + Usuario.class.getName());
		}
		return obj;

	}

	public Usuario inserir(Usuario usuario) {

		String proprietarioReferencia = "";
		String remetente = "";
		String destinatario = usuario.getEmail();
		String tituloDoEmail = "E-mail de cadastro";
		String corpoDoEmail = "Prezado(a) " + usuario.getName()
				+ ", seja bem-vindo(a) a casa do estudante.\nSuas credenciais para acessar a plataforma são:\nLogin: "
				+ usuario.getEmail() + "\nSenha: " + usuario.getPassword() + "\nAcessa a www.casadoestudante.com.br";

		EmailDto emailDto = new EmailDto(proprietarioReferencia, remetente, destinatario, tituloDoEmail, corpoDoEmail);

		expedidorEmailFeingCLeint.enviarEmail(emailDto);
		usuario.setId(null);
		return usuarioRepository.save(usuario);

	}

	public Usuario atualizar(Usuario obj) {

		Usuario newObj = buscarPorId(obj.getId());
		updateData(newObj, obj);

		newObj.getTarefas().forEach(x -> tarefaRepository.save(x));
		return usuarioRepository.save(newObj);
	}

	public void apagar(Long id) {
		buscarPorId(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir esse estudante");
		}
	}

	public Usuario fromDTO(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setName(dto.getName());
		usuario.setEmail(dto.getEmail());
		usuario.setPassword(dto.getPassword());
		usuario.setCurso(dto.getCurso());
		return usuario;

	}

	public Usuario fromDTO(NovoUsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setName(dto.getName());
		usuario.setEmail(dto.getEmail());
		usuario.setPassword(dto.getPassword());
		usuario.setCurso(dto.getCurso());
		usuario.setDiaDeCozinhar(DiaSemana.toEnum(dto.getDiaDeCozinhar()));
		usuario.setDiaDeLimpar(DiaSemana.toEnum(dto.getDiaDeLimpar()));

		return usuario;

	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setCurso(obj.getCurso());
		newObj.setDiaDeCozinhar(obj.getDiaDeCozinhar());
		newObj.setDiaDeLimpar(obj.getDiaDeLimpar());
//		newObj.setTarefas(obj.getTarefas());
	}

}
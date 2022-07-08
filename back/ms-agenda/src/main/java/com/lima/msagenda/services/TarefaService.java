package com.lima.msagenda.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lima.msagenda.enums.TipoTarefa;
import com.lima.msagenda.feingClients.ExpedidorEmailFeingCLeint;
import com.lima.msagenda.models.Tarefa;
import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.EmailDto;
import com.lima.msagenda.models.dtos.NovaTarefaDTO;
import com.lima.msagenda.models.dtos.TarefaDTO;
import com.lima.msagenda.repositories.TarefaRepository;
import com.lima.msagenda.repositories.UsuarioRepository;
import com.lima.msagenda.services.exceptions.DataIntegrityException;
import com.lima.msagenda.services.exceptions.ObjectNotFoundException;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ExpedidorEmailFeingCLeint expedidorEmailFeingCLeint;

	public List<Tarefa> buscarTodos() {
		return tarefaRepository.findAll();
	}

	public Tarefa buscarPorId(Long id) {
		Optional<Tarefa> obj = tarefaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Tarefa não encontrado! Id: " + id + ", Tipo: " + Tarefa.class.getName()));
	}
	
	@Transactional
	public Tarefa inserir(Tarefa tarefa) {
		List<Usuario> estudantes = usuarioRepository.findAll();

		for (int i = 0; i < estudantes.size(); i++) {

			String proprietarioReferencia = "";
			String remetente = "";
			String destinatario = estudantes.get(i).getEmail();
			String tituloDoEmail = "Tarefa Realizada";
			String corpoDoEmail = "O(a) aluno(a) " + tarefa.getUsuario().getName() + " fez a " + tarefa.getTipo()
					+ " às " + tarefa.getData().getHour() + ":" + tarefa.getData().getMinute();

			EmailDto emailDto = new EmailDto(proprietarioReferencia, remetente, destinatario, tituloDoEmail,
					corpoDoEmail);

			expedidorEmailFeingCLeint.enviarEmail(emailDto);

		}

//		tarefa.setId(null);
		tarefaRepository.save(tarefa);
		usuarioRepository.save(tarefa.getUsuario());
		return tarefa;

	}

	public Tarefa atualizar(Tarefa obj) {
		Tarefa newObj = buscarPorId(obj.getId());
		updateData(newObj, obj);

		return tarefaRepository.save(newObj);
	}

	public void apagar(Long id) {
		buscarPorId(id);
		try {
			tarefaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir essa tarefa");
		}
	}

	public Tarefa fromDTO(TarefaDTO objDTO) {
		Tarefa tarefa = new Tarefa();
		Usuario usuario = usuarioService.buscarPorId(objDTO.getUsuario());
		tarefa.setUsuario(usuario);
		tarefa.setData(objDTO.getData());
		tarefa.setStatus(objDTO.getStatus());
		tarefa.setTipo(TipoTarefa.toEnum(objDTO.getTipo()));

		return tarefa;
	}

	public Tarefa fromDTO(NovaTarefaDTO objDTO) {
		Tarefa tarefa = new Tarefa();
		Usuario usuario = usuarioService.buscarPorEmail(objDTO.getUsuarioEmail());
		tarefa.setUsuario(usuario);
		tarefa.setData(objDTO.getData());
		tarefa.setStatus(objDTO.getStatus());
		tarefa.setTipo(TipoTarefa.toEnum(objDTO.getTipo()));

		return tarefa;
	}

	private void updateData(Tarefa newObj, Tarefa obj) {
		newObj.setData(obj.getData());
		newObj.setStatus(obj.getStatus());
		newObj.setTipo(obj.getTipo());
		newObj.setUsuario(obj.getUsuario());
	}

}

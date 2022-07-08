package com.lima.msagenda.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lima.msagenda.models.Tarefa;
import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.NovaTarefaDTO;
import com.lima.msagenda.services.TarefaService;
import com.lima.msagenda.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService service;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Tarefa>> buscarTodos() {
		List<Tarefa> lista = service.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
		Tarefa obj = service.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Tarefa> inserir(@RequestBody @Valid NovaTarefaDTO novaTarefaDTO) {
		String email = novaTarefaDTO.getUsuarioEmail();
		Usuario usuario = usuarioService.buscarPorEmail(email);
		Tarefa obj = service.fromDTO(novaTarefaDTO);
		obj.setUsuario(usuario);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Tarefa> inserir(@RequestBody @Valid TarefaDTO tarefaDTO) {
//		Tarefa obj = service.fromDTO(tarefaDTO);
//		obj = service.inserir(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Tarefa> atualizar(@Valid @RequestBody NovaTarefaDTO objDto, @PathVariable Long id) {
		Tarefa obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.apagar(id);
		return ResponseEntity.noContent().build();
	}

}

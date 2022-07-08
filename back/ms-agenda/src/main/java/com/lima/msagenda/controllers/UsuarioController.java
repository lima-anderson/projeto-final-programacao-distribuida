package com.lima.msagenda.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lima.msagenda.models.Usuario;
import com.lima.msagenda.models.dtos.NovoUsuarioDTO;
import com.lima.msagenda.models.dtos.UsuarioDTO;
import com.lima.msagenda.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		List<Usuario> lista = service.buscarTodos();
		List<UsuarioDTO> listaDto = lista.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario obj = service.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
		Usuario obj = service.buscarPorEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
		Usuario obj = service.fromDTO(novoUsuarioDTO);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody @Valid NovoUsuarioDTO novoUsuarioDTO) {
		Usuario obj = service.fromDTO(novoUsuarioDTO);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		service.apagar(id);
		return ResponseEntity.noContent().build();
	}

}

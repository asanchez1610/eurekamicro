package com.sanchez.microservicios.app.seguridad.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanchez.microservicios.app.seguridad.models.entity.Usuario;
import com.sanchez.microservicios.app.seguridad.service.UsuarioService;

@RestController
public class SeguridadController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/users")
	public ResponseEntity<?> listarUsuario() {
		return ResponseEntity.ok().body(usuarioService.findAll());
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		usuarioService.createUser(usuario);
		usuario.setUserPassword(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario.isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.noContent().build();
	}
	
}

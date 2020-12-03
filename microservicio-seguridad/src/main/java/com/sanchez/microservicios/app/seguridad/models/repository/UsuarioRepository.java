package com.sanchez.microservicios.app.seguridad.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sanchez.microservicios.app.seguridad.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByUserNameIgnoreCase(String userName);
	
}

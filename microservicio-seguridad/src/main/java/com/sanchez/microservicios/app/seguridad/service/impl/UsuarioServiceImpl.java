package com.sanchez.microservicios.app.seguridad.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanchez.microservicios.app.seguridad.models.entity.Usuario;
import com.sanchez.microservicios.app.seguridad.models.repository.UsuarioRepository;
import com.sanchez.microservicios.app.seguridad.service.UsuarioService;
import com.sanchez.microservicios.commons.service.CommonServiceImpl;

@Service
public class UsuarioServiceImpl extends CommonServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {

	@Override
	public Optional<Usuario> findByUserName(String userName) {
		return repository.findByUserNameIgnoreCase(userName);
	}

	@Override
	@Transactional
	public Usuario createUser(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(usuario.getUserPassword());
		usuario.setPassword(hashedPassword);
		return repository.save(usuario);
	}

}

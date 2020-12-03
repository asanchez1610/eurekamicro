package com.sanchez.microservicios.app.seguridad.service;

import java.util.Optional;

import com.sanchez.microservicios.app.seguridad.models.entity.Usuario;
import com.sanchez.microservicios.commons.service.CommonService;

public interface UsuarioService extends CommonService<Usuario>{

	public Optional<Usuario> findByUserName(String userName);

	public Usuario createUser(Usuario usuario);


}

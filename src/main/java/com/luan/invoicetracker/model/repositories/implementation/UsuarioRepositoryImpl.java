package com.luan.invoicetracker.model.repositories.implementation;

import java.util.Collection;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.luan.invoicetracker.model.Usuario;
import com.luan.invoicetracker.model.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UsuarioRepositoryImpl implements UsuarioRepository<Usuario> {
	
	private final NamedParameterJdbcTemplate jdb; 

	@Override
	public Usuario create(Usuario data) {
		//verificar se o email é unico
		// salvar novo usuário
		// add Role para Usuario
		// mandar verificao Url
		// mandar url na tabela de verificao
		// mandar email para o usuario com a url de verificação
		// retornar o novo usuario
		// se ocorrer um erro, mandar uma msg com o erro
		return null;
	}

	@Override
	public Collection<Usuario> list(int page, int pageSize) {
		return null;
	}

	@Override
	public Usuario get(Long id) {
		return null;
	}

	@Override
	public Usuario update(Usuario data) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		return null;
	}

}

package com.luan.invoicetracker.model.repositories.implementation;

import java.util.Collection;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.luan.invoicetracker.exception.ApiException;
import com.luan.invoicetracker.model.Usuario;
import com.luan.invoicetracker.model.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UsuarioRepositoryImpl implements UsuarioRepository<Usuario> {
	
	private static final String COUNT_USER_EMAIL_QUERY = "";
	private final NamedParameterJdbcTemplate jdbc; 

	@Override
	public Usuario create(Usuario usuario) {
		//verificar se o email é unico
		if (getEmailCount(usuario.getEmail().trim().toLowerCase()) > 0) throw new ApiException("Email já está em uso, use outro email e tente novamente");
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

	//@Query("SELECT COUNT(*) FROM Usuario u")
	private Integer getEmailCount(String email) {
		return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
	}

}

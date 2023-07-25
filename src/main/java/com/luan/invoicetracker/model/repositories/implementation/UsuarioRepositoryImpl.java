package com.luan.invoicetracker.model.repositories.implementation;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.luan.invoicetracker.enums.RoleTipo;
import com.luan.invoicetracker.exception.ApiException;
import com.luan.invoicetracker.model.Role;
import com.luan.invoicetracker.model.Usuario;
import com.luan.invoicetracker.model.repositories.RoleRepository;
import com.luan.invoicetracker.model.repositories.UsuarioRepository;
import com.luan.invoicetracker.query.UsuarioQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UsuarioRepositoryImpl implements UsuarioRepository<Usuario> {

	private final NamedParameterJdbcTemplate jdbc;
	
	private final RoleRepository<Role> roleRepository;
	
	@Autowired
	private UsuarioQuery query;
	
	@Autowired
	private RoleTipo roleTipo;

	@Override
	public Usuario create(Usuario usuario) {
		//verificar se o email é unico
		if (getEmailCount(usuario.getEmail().trim().toLowerCase()) > 0) throw new ApiException("Email já está em uso, use outro email e tente novamente");
		// salvar novo usuário
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			SqlParameterSource parameter = getSqlParameterSource(usuario);
			jdbc.update(UsuarioQuery.INSERT_USUARIO_QUERY, parameter, holder);
			usuario.setId(Objects.requireNonNull(holder.getKey().longValue()));
			// add Role para Usuario
			roleRepository.addRoleToUsuario(usuario.getId(), roleTipo.ROLE_USUARIO.name());
		} catch (EmptyResultDataAccessException exception) {
			
		} catch (Exception e) {

		}
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
		return jdbc.queryForObject(UsuarioQuery.COUNT_USUARIO_EMAIL_QUERY, Map.of("email", email), Integer.class);
	}

	private SqlParameterSource getSqlParameterSource(Usuario usuario) {
		return null;
	}

}

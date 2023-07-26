package com.luan.invoicetracker.model.repositories.implementation;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.luan.invoicetracker.enums.RoleTipo;
import com.luan.invoicetracker.exception.ApiException;
import com.luan.invoicetracker.model.Role;
import com.luan.invoicetracker.model.repositories.RoleRepository;
import com.luan.invoicetracker.rowmapper.RoleRowMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {
	
	private static final String INSERT_ROLE_TO_USUARIO_QUERY = "";
	private static final String SELECT_ROLE_BY_NOME_QUERY = "";
	private final NamedParameterJdbcTemplate jdbc;
	private final RoleRepository<Role> roleRepository;
	private final BCryptPasswordEncoder encoder;
	
	@Autowired
	private RoleTipo roleTipo;

	@Override
	public Role create(Role data) {
		return null;
	}

	@Override
	public Collection<Role> list(int page, int pageSize) {
		return null;
	}

	@Override
	public Role get(Long id) {
		return null;
	}

	@Override
	public Role update(Role data) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		return null;
	}

	@Override
	public void addRoleToUsuario(Long usuarioId, String roleNome) {
		log.info("Adicionado cargo {} ao usuario id: {}", roleNome, usuarioId);
		try {
			Role role = jdbc.queryForObject(SELECT_ROLE_BY_NOME_QUERY, Map.of("roleNome", roleNome), new RoleRowMapper());
			jdbc.update(INSERT_ROLE_TO_USUARIO_QUERY, Map.of("usuarioId", usuarioId, "roleId", role.getId()));
		} catch (EmptyResultDataAccessException exception) {
			 throw new ApiException("Não foi encontrado nenhum cargo com esse nome." + roleTipo.ROLE_USUARIO.name());
		} catch (Exception e) {
			 throw new ApiException("Ocorreu um erro, por favor tente novamente");
		
		}
	}

	@Override
	public Role getRoleByUsuarioId(Long usuarioId) {
		return null;
	}

	@Override
	public Role getRoleByUsuarioEmail(String email) {
		return null;
	}

	@Override
	public void update(Long usuarioId, String roleNome) {
	}
}

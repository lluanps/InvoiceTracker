package com.luan.invoicetracker.model.repositories.implementation;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luan.invoicetracker.enums.RoleTipo;
import com.luan.invoicetracker.enums.VerificacaoTipo;
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
	private final BCryptPasswordEncoder encoder;
	
	
	@Autowired
	private UsuarioQuery query;
	
	@Autowired
	private RoleTipo roleTipo;

	@Autowired
	private VerificacaoTipo verificacaoTipo;
	
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
			
			// mandar verificao Url
			String verificaUrl = getVerificaUrl(UUID.randomUUID().toString(), verificacaoTipo.CONTA.getTipo());
			
			// mandar url na tabela de verificao
			jdbc.update(UsuarioQuery.INSERT_CONTA_VERIFICACAO_URL_QUERY, Map.of("usuarioId", usuario.getId(), "url", verificaUrl));
			
			// mandar email para o usuario com a url de verificação
			//emailService.mandaVerificacaoUrl(usuario.getNome(), usuario.getEmail(), verificaUrl, true, verificacaoTipo.CONTA);
			usuario.setAtivo(false);
			usuario.setNotBloqueado(false);
			
			// retornar o novo usuario
			return usuario;
			
			// se ocorrer um erro, mandar uma msg com o erro
		} catch (Exception e) {
			 throw new ApiException("Ocorreu um erro, por favor tente novamente");
		}
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
		return new MapSqlParameterSource()
				.addValue("nome", usuario.getNome())
				.addValue("sobrenome", usuario.getSobrenome())
				.addValue("email", usuario.getEmail())
				.addValue("senha", encoder.encode(usuario.getSenha()));
	}
	
	private String getVerificaUrl(String key, String tipo) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/usuario/verifica/" + tipo + "/" + key).toString();
	}

}

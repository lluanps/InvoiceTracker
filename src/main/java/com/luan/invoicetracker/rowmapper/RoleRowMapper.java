package com.luan.invoicetracker.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.luan.invoicetracker.model.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	@Nullable
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Role.builder()
				.id(rs.getLong("id"))
				.nome(rs.getString("nome"))
				.permissao(rs.getString("permissao"))
				.build();
	}

}

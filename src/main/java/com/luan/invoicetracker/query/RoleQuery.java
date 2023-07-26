package com.luan.invoicetracker.query;

public class RoleQuery {

	public static final String INSERT_ROLE_TO_USUARIO_QUERY = "INSERT INTO usuario_roles (usuario_id, role_id) VALUES (:usuario_id, :role_id)";
	public static final String SELECT_ROLE_BY_NOME_QUERY = "SELECT * FROM Roles WHERE nome = :nome";
	
}

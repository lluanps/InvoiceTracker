package com.luan.invoicetracker.query;

public class UsuarioQuery {
	
	public static final String COUNT_USUARIO_EMAIL_QUERY = "INSERT INTO Usuario (nome, sobrenome, email, senha) VALUES (:nome, :sobrenome, :email, :senha)";
	public static final String INSERT_USUARIO_QUERY = "SELECT COUNT(*) FROM Usuario WHERE email = :email";
	public static final String INSERT_CONTA_VERIFICACAO_URL_QUERY = "INSERT INTO conta_verificao (usuario_id, url) VALUES (:usuarioId, :url)";

}

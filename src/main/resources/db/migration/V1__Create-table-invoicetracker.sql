CREATE SCHEMA IF NOT EXISTS invoicetracker;

SET NAMES 'UTF8';

DROP TABLE IF EXISTS Usuario;

CREATE TABLE Usuario
(
	id 						SERIAL PRIMARY KEY,
	nome 					VARCHAR(50) NOT NULL,
	sobrenome 				VARCHAR(50) NOT NULL,
	email 					VARCHAR(100) NOT NULL,
	senha	 				VARCHAR(255) DEFAULT NULL,
	endereco 				VARCHAR(255) DEFAULT NULL,
	telefone 				VARCHAR(12) DEFAULT NULL,
	titulo 					VARCHAR(50) DEFAULT NULL,
	biografia 				VARCHAR(255) DEFAULT NULL,
	ativo 					BOOLEAN DEFAULT FALSE,
	desbloqueado 			BOOLEAN DEFAULT TRUE,
	dupla_autentificacao 	BOOLEAN DEFAULT FALSE,
	data_criacao 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	img_url 				VARCHAR(255) DEFAULT 'https://img.freepik.com/free-icon/user_318-159711.jpg?size=626&ext=jpg&ga=GA1.1.993812922.1690236522&semt=ais',
	CONSTRAINT UNIQUE_Usuario_Email UNIQUE (email)
);

DROP TABLE IF EXISTS Roles;

CREATE TABLE Roles
(
	id 				SERIAL PRIMARY KEY,
	nome 			VARCHAR(50) NOT NULL,
	permissao 		VARCHAR(255) NOT NULL,
	CONSTRAINT UNIQUE_Roles_Nome UNIQUE (nome)
);

DROP TABLE IF EXISTS usuario_roles;

CREATE TABLE usuario_roles
(
	id 				SERIAL PRIMARY KEY,
	usuario_id		INTEGER  NOT NULL,
	role_id			INTEGER  NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (role_id) REFERENCES Roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT UNIQUE_usuario_roles_Usuario_Id UNIQUE (usuario_id)
);

DROP TABLE IF EXISTS Events;

CREATE TABLE Events
(
	id 				SERIAL PRIMARY KEY,
	tipo 			VARCHAR(50) NOT NULL CHECK(tipo IN (
														'LOGIN_TENTANTIVA', 'LOGIN_FALHA', 'LOGIN_SUCESSO', 
														'USUARIO_UPDATE', 'USUARIO_FOTO_UPDATE', 
														'ROLE_UPDATE', 
														'CONTA_CONFIG_UPDATE', 'CONTA_SENHA_UPDATE', 
														'DUPLA_AUTENTIFICAO_UPDATE'
					)),
	descricao 		VARCHAR(255) NOT NULL,
	CONSTRAINT UNIQUE_Events_Tipo UNIQUE (tipo)
);

DROP TABLE IF EXISTS usuario_events;

CREATE TABLE usuario_events
(
	id 				SERIAL PRIMARY KEY,
	usuario_id		INTEGER  NOT NULL,
	event_id		INTEGER  NOT NULL,
	equipamento		VARCHAR(100) DEFAULT NULL,
	ip				VARCHAR(100) DEFAULT NULL,
	data_criacao 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (event_id) REFERENCES Events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS conta_verificao;

CREATE TABLE conta_verificao
(
	id 				SERIAL PRIMARY KEY,
	usuario_id		INTEGER  NOT NULL,
	url				VARCHAR(255) NOT NULL,
	-- data      		TIMESTAMP NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT UNIQUE_conta_verificao_Usuario_Id UNIQUE (usuario_id),
	CONSTRAINT UNIQUE_conta_verificao_Url UNIQUE (url)
);

DROP TABLE IF EXISTS reset_senha;

CREATE TABLE reset_senha
(
	id 				SERIAL PRIMARY KEY,
	usuario_id		INTEGER  NOT NULL,
	url				VARCHAR(255) NOT NULL,
	data_limite     TIMESTAMP NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT UNIQUE_reset_senha_Usuario_Id UNIQUE (usuario_id),
	CONSTRAINT UNIQUE_reset_senha_Url UNIQUE (url)
);

DROP TABLE IF EXISTS dupla_verificacao;

CREATE TABLE dupla_verificacao
(
	id 				SERIAL PRIMARY KEY,
	usuario_id		INTEGER  NOT NULL,
	codigo			VARCHAR(10) NOT NULL,
	data_limite     TIMESTAMP NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT UNIQUE_dupla_verificacao_Usuario_Id UNIQUE (usuario_id),
	CONSTRAINT UNIQUE_dupla_verificacao_Url UNIQUE (codigo)
);

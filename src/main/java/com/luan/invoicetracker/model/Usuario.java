package com.luan.invoicetracker.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;
	
	@NotEmpty(message = "Campo sobrenome é obrigatório")
	private String sobrenome;
	
	@NotEmpty(message = "Campo email é obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Campo email é obrigatório")
	private String senha;
	private String endereco;
	private String telefone;
	private String titulo;
	private String biografia;
	private boolean ativo;
	
	/*desbloqueado*/
	private boolean isNotBloqueado;
	
	private boolean isduplaAutentificacao;
	private LocalDateTime dataCriacao;
	private String imgUrl;

	
	

}

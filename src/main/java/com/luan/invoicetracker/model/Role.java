package com.luan.invoicetracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Role {
	
	private Long id;
	private String nome;
	private String permissao;

}

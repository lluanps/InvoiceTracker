package com.luan.invoicetracker.model.repositories;

import java.util.Collection;

import com.luan.invoicetracker.model.Usuario;

public interface UsuarioRepository<T extends Usuario> {
	/* CRUD básico */
	T create(T data);
	Collection<T> list(int page, int pageSize);
	T get(Long id);
	T update(T data);
	Boolean delete(Long id);
	/* ----///---- */
	
}

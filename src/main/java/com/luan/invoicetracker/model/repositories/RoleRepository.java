package com.luan.invoicetracker.model.repositories;

import java.util.Collection;

import com.luan.invoicetracker.model.Role;

public interface RoleRepository<T extends Role> {
	/* CRUD básico */
	T create(T data);
	Collection<T> list(int page, int pageSize);
	T get(Long id);
	T update(T data);
	Boolean delete(Long id);
	/* ----///---- */ 
	
	void addRoleToUsuario(Long usuarioId, String roleNome);
	Role getRoleByUsuarioId(Long usuarioId);
	Role getRoleByUsuarioEmail(String email);
	void update  (Long usuarioId, String roleNome);



}

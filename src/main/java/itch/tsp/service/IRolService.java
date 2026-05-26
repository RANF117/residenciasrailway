package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.Rol;

public interface IRolService {

	List<Rol> buscarTodosRol();

	List<Rol> buscarTodosActivos();

	void guardarRol(Rol rol);

	Rol buscarPorIdRol(Integer id);

	void eliminarRol(Integer id);
}
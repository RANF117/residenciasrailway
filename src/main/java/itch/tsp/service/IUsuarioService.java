package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.Usuario;

public interface IUsuarioService {

	List<Usuario> buscarTodosUsu();

	List<Usuario> buscarTodosActivos();

	List<Usuario> buscarTodosInactivos();

	void guardarUsuario(Usuario usuario);

	Usuario buscarPorIdUsu(Integer id);

	void eliminarUsuario(Integer id);

	void activarUsuario(Integer id);

	Usuario buscarPorUsername(String username);

	boolean existeJefeDivisionActivo();
}
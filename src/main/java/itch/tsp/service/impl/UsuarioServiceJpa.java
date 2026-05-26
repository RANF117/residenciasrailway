package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.Usuario;
import itch.tsp.repository.UsuarioRepository;
import itch.tsp.service.IUsuarioService;

@Primary
@Service
public class UsuarioServiceJpa implements IUsuarioService {

	@Autowired
	private UsuarioRepository repoUsuario;

	@Override
	public List<Usuario> buscarTodosUsu() {
		return repoUsuario.findAll();
	}

	@Override
	public List<Usuario> buscarTodosActivos() {
		return repoUsuario.findByEstatus(1);
	}

	@Override
	public List<Usuario> buscarTodosInactivos() {
		return repoUsuario.findByEstatus(0);
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		repoUsuario.save(usuario);
	}

	@Override
	public Usuario buscarPorIdUsu(Integer id) {
		Optional<Usuario> optional = repoUsuario.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		Usuario usuario = repoUsuario.findById(id).orElse(null);

		if (usuario != null) {
			usuario.setEstatus(0);
			repoUsuario.save(usuario);
		}
	}

	@Override
	public void activarUsuario(Integer id) {
		Usuario usuario = repoUsuario.findById(id).orElse(null);

		if (usuario != null) {
			usuario.setEstatus(1);
			repoUsuario.save(usuario);
		}
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return repoUsuario.findByUsernameAndEstatus(username, 1);
	}

	@Override
	public boolean existeJefeDivisionActivo() {
		return repoUsuario.existsByRol_NombreAndEstatus("JEFE_DIVISION", 1);
	}
}
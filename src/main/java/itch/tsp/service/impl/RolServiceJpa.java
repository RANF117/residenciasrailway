package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.Rol;
import itch.tsp.repository.RolRepository;
import itch.tsp.service.IRolService;

@Primary
@Service
public class RolServiceJpa implements IRolService {

	@Autowired
	private RolRepository repoRol;

	@Override
	public List<Rol> buscarTodosRol() {
		return repoRol.findAll();
	}

	@Override
	public List<Rol> buscarTodosActivos() {
		return repoRol.findByEstatus(1);
	}

	@Override
	public void guardarRol(Rol rol) {
		repoRol.save(rol);
	}

	@Override
	public Rol buscarPorIdRol(Integer id) {
		Optional<Rol> optional = repoRol.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void eliminarRol(Integer id) {
		Rol rol = repoRol.findById(id).orElse(null);

		if (rol != null) {
			rol.setEstatus(0);
			repoRol.save(rol);
		}
	}
}
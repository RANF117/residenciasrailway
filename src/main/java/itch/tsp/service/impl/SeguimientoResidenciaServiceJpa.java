package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.SeguimientoResidencia;
import itch.tsp.repository.SeguimientoResidenciaRepository;
import itch.tsp.service.ISeguimientoResidenciaService;

@Primary
@Service
public class SeguimientoResidenciaServiceJpa implements ISeguimientoResidenciaService {

	@Autowired
	private SeguimientoResidenciaRepository repoSeguimientoResidencia;

	@Override
	public List<SeguimientoResidencia> buscarTodosSeg() {
		return repoSeguimientoResidencia.findByEstatus(1);
	}

	@Override
	public List<SeguimientoResidencia> buscarSeguimientosActivos(String tipoSeguimiento) {
		if (tipoSeguimiento != null && !tipoSeguimiento.isBlank()) {
			return repoSeguimientoResidencia.findByTipoSeguimientoAndEstatus(tipoSeguimiento, 1);
		}
		return repoSeguimientoResidencia.findByEstatus(1);
	}

	@Override
	public List<SeguimientoResidencia> buscarTodosSeguimientos(String tipoSeguimiento) {
		if (tipoSeguimiento != null && !tipoSeguimiento.isBlank()) {
			return repoSeguimientoResidencia.findByTipoSeguimiento(tipoSeguimiento);
		}
		return repoSeguimientoResidencia.findAll();
	}

	@Override
	public List<SeguimientoResidencia> buscarTodosIncluyendoInactivos() {
		return repoSeguimientoResidencia.findAll();
	}

	@Override
	public List<SeguimientoResidencia> buscarPorProyectoResidencia(Integer idProyectoResidencia) {
		return repoSeguimientoResidencia.findByProyectoResidenciaIdProyectoResidenciaAndEstatus(idProyectoResidencia, 1);
	}

	@Override
	public SeguimientoResidencia buscarPorProyectoYTipo(Integer idProyectoResidencia, String tipoSeguimiento) {
		return repoSeguimientoResidencia.findByProyectoResidenciaIdProyectoResidenciaAndTipoSeguimientoAndEstatus(
				idProyectoResidencia, tipoSeguimiento, 1
		);
	}

	@Override
	public void guardarSeguimientoResidencia(SeguimientoResidencia seguimientoResidencia) {
		repoSeguimientoResidencia.save(seguimientoResidencia);
	}

	@Override
	public SeguimientoResidencia buscarPorIdSeg(Integer id) {
		Optional<SeguimientoResidencia> optional = repoSeguimientoResidencia.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void eliminarSeguimientoResidencia(Integer id) {
		SeguimientoResidencia seguimientoResidencia = repoSeguimientoResidencia.findById(id).orElse(null);

		if (seguimientoResidencia != null) {
			seguimientoResidencia.setEstatus(0);
			repoSeguimientoResidencia.save(seguimientoResidencia);
		}
	}

	@Override
	public void activarSeguimientoResidencia(Integer id) {
		SeguimientoResidencia seguimientoResidencia = repoSeguimientoResidencia.findById(id).orElse(null);

		if (seguimientoResidencia != null) {
			seguimientoResidencia.setEstatus(1);
			repoSeguimientoResidencia.save(seguimientoResidencia);
		}
	}
}
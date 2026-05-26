package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.AsesorInterno;
import itch.tsp.repository.AsesorInternoRepository;
import itch.tsp.repository.ProyectoResidenciaRepository;
import itch.tsp.service.IAsesorInternoService;

@Primary
@Service
public class AsesorInternoServiceJpa implements IAsesorInternoService {

	@Autowired
	private AsesorInternoRepository repoAsesorInterno;

	@Autowired
	private ProyectoResidenciaRepository repoProyectoResidencia;

	@Override
	public List<AsesorInterno> buscarTodosAsInt() {
		return repoAsesorInterno.findByEstatus(1);
	}

	@Override
	public List<AsesorInterno> buscarAsesoresInternosActivosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoAsesorInterno.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoAsesorInterno.findByEstatus(1);
	}

	@Override
	public List<AsesorInterno> buscarTodosAsesoresInternosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoAsesorInterno.findByNombreContainingIgnoreCase(nombre);
		}
		return repoAsesorInterno.findAll();
	}

	@Override
	public List<AsesorInterno> buscarTodosIncluyendoInactivos() {
		return repoAsesorInterno.findAll();
	}

	@Override
	public void guardarAsesorInterno(AsesorInterno asesorInterno) {
		repoAsesorInterno.save(asesorInterno);
	}

	@Override
	public AsesorInterno buscarPorIdAsInt(Integer id) {
		Optional<AsesorInterno> optional = repoAsesorInterno.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarAsesorInterno(Integer id) {
		AsesorInterno asesorInterno = repoAsesorInterno.findById(id).orElse(null);

		if (asesorInterno != null) {
			asesorInterno.setEstatus(0);
			repoAsesorInterno.save(asesorInterno);
		}
	}

	@Override
	public void activarAsesorInterno(Integer id) {
		AsesorInterno asesorInterno = repoAsesorInterno.findById(id).orElse(null);

		if (asesorInterno != null) {
			asesorInterno.setEstatus(1);
			repoAsesorInterno.save(asesorInterno);
		}
	}

	@Override
	public boolean asesorInternoTieneVinculoActivo(Integer id) {
		return repoProyectoResidencia.existsByAsesorInterno_IdAsesorInternoAndEstatus(id, 1);
	}
}
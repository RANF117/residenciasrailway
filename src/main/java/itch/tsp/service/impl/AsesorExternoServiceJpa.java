package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.AsesorExterno;
import itch.tsp.repository.AsesorExternoRepository;
import itch.tsp.repository.ProyectoResidenciaRepository;
import itch.tsp.service.IAsesorExternoService;

@Primary
@Service
public class AsesorExternoServiceJpa implements IAsesorExternoService {

	@Autowired
	private AsesorExternoRepository repoAsesorExterno;

	@Autowired
	private ProyectoResidenciaRepository repoProyectoResidencia;

	@Override
	public List<AsesorExterno> buscarTodosAsExt() {
		return repoAsesorExterno.findByEstatus(1);
	}

	@Override
	public List<AsesorExterno> buscarAsesoresExternosActivosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoAsesorExterno.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoAsesorExterno.findByEstatus(1);
	}

	@Override
	public List<AsesorExterno> buscarTodosAsesoresExternosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoAsesorExterno.findByNombreContainingIgnoreCase(nombre);
		}
		return repoAsesorExterno.findAll();
	}

	@Override
	public List<AsesorExterno> buscarTodosIncluyendoInactivos() {
		return repoAsesorExterno.findAll();
	}

	@Override
	public void guardarAsesorExterno(AsesorExterno asesorExterno) {
		repoAsesorExterno.save(asesorExterno);
	}

	@Override
	public AsesorExterno buscarPorIdAsExt(Integer id) {
		Optional<AsesorExterno> optional = repoAsesorExterno.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarAsesorExterno(Integer id) {
		AsesorExterno asesorExterno = repoAsesorExterno.findById(id).orElse(null);

		if (asesorExterno != null) {
			asesorExterno.setEstatus(0);
			repoAsesorExterno.save(asesorExterno);
		}
	}

	@Override
	public void activarAsesorExterno(Integer id) {
		AsesorExterno asesorExterno = repoAsesorExterno.findById(id).orElse(null);

		if (asesorExterno != null) {
			asesorExterno.setEstatus(1);
			repoAsesorExterno.save(asesorExterno);
		}
	}

	@Override
	public boolean asesorExternoTieneVinculoActivo(Integer id) {
		return repoProyectoResidencia.existsByAsesorExterno_IdAsesorExternoAndEstatus(id, 1);
	}
}
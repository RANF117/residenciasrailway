package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.Residente;
import itch.tsp.repository.PropuestaProyectoRepository;
import itch.tsp.repository.ProyectoResidenciaRepository;
import itch.tsp.repository.ResidenteRepository;
import itch.tsp.service.IResidenteService;

@Primary
@Service
public class ResidenteServiceJpa implements IResidenteService {

	@Autowired
	private ResidenteRepository repoResidente;

	@Autowired
	private ProyectoResidenciaRepository repoProyectoResidencia;

	@Autowired
	private PropuestaProyectoRepository repoPropuestaProyecto;

	@Override
	public List<Residente> buscarTodosRes() {
		return repoResidente.findByEstatus(1);
	}

	@Override
	public List<Residente> buscarResidentesActivosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoResidente.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoResidente.findByEstatus(1);
	}

	@Override
	public List<Residente> buscarTodosResidentesPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoResidente.findByNombreContainingIgnoreCase(nombre);
		}
		return repoResidente.findAll();
	}

	@Override
	public List<Residente> buscarTodosIncluyendoInactivos() {
		return repoResidente.findAll();
	}

	@Override
	public void guardarResidente(Residente residente) {
		repoResidente.save(residente);
	}

	@Override
	public Residente buscarPorIdRes(Integer id) {
		Optional<Residente> optional = repoResidente.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarResidente(Integer id) {
		Residente residente = repoResidente.findById(id).orElse(null);

		if (residente != null) {
			residente.setEstatus(0);
			repoResidente.save(residente);
		}
	}

	@Override
	public void activarResidente(Integer id) {
		Residente residente = repoResidente.findById(id).orElse(null);

		if (residente != null) {
			residente.setEstatus(1);
			repoResidente.save(residente);
		}
	}
	
	@Override
	public List<Residente> buscarResidentesConProyectoPorNombre(String nombre) {
	    return repoResidente.buscarResidentesConProyectoPorNombre(nombre);
	}

	@Override
	public List<Residente> buscarResidentesSinProyectoPorNombre(String nombre) {
		return repoResidente.buscarResidentesSinProyectoPorNombre(nombre);
	}

	@Override
	public boolean residenteTieneVinculoActivo(Integer id) {
		boolean tieneProyectoActivo = repoProyectoResidencia.existsByResidente_IdResidenteAndEstatus(id, 1);
		boolean tienePropuestaActiva = repoPropuestaProyecto.existsByResidente_IdResidenteAndEstatus(id, 1);

		return tieneProyectoActivo || tienePropuestaActiva;
	}
}
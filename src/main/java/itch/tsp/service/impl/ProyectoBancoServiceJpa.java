package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.ProyectoBanco;
import itch.tsp.repository.ProyectoBancoRepository;
import itch.tsp.service.IProyectoBancoService;

@Primary
@Service
public class ProyectoBancoServiceJpa implements IProyectoBancoService {

	@Autowired
	private ProyectoBancoRepository repoProyectoBanco;

	@Override
	public List<ProyectoBanco> buscarTodosProyBan() {
		return repoProyectoBanco.findByEstatus(1);
	}

	@Override
	public List<ProyectoBanco> buscarProyectosBancoActivos(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tienePeriodo = periodo != null && !periodo.isBlank();

		if (tieneNombre) {
			return repoProyectoBanco.findByNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
		}

		if (tienePeriodo) {
		    return repoProyectoBanco.findByPeriodoContainingIgnoreCaseAndEstatus(periodo, 1);
		}

		return repoProyectoBanco.findByEstatus(1);
	}

	@Override
	public List<ProyectoBanco> buscarTodosProyectosBanco(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tienePeriodo = periodo != null && !periodo.isBlank();

		if (tieneNombre) {
			return repoProyectoBanco.findByNombreProyectoContainingIgnoreCase(nombreProyecto);
		}

		if (tienePeriodo) {
		    return repoProyectoBanco.findByPeriodoContainingIgnoreCase(periodo);
		}

		return repoProyectoBanco.findAll();
	}

	@Override
	public List<ProyectoBanco> buscarTodosIncluyendoInactivos() {
		return repoProyectoBanco.findAll();
	}

	@Override
	public void guardarProyectoBanco(ProyectoBanco proyectoBanco) {
		repoProyectoBanco.save(proyectoBanco);
	}

	@Override
	public ProyectoBanco buscarPorIdProyBan(Integer id) {
		Optional<ProyectoBanco> optional = repoProyectoBanco.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarProyectoBanco(Integer id) {
		ProyectoBanco proyectoBanco = repoProyectoBanco.findById(id).orElse(null);

		if (proyectoBanco != null) {
			proyectoBanco.setEstatus(0);
			repoProyectoBanco.save(proyectoBanco);
		}
	}

	@Override
	public void activarProyectoBanco(Integer id) {
		ProyectoBanco proyectoBanco = repoProyectoBanco.findById(id).orElse(null);

		if (proyectoBanco != null) {
			proyectoBanco.setEstatus(1);
			repoProyectoBanco.save(proyectoBanco);
		}
	}
	@Override
	public List<ProyectoBanco> buscarProyectosBancoDisponibles() {
		return repoProyectoBanco.buscarProyectosBancoDisponibles();
	}

	@Override
	public List<ProyectoBanco> buscarProyectosBancoDisponiblesPorNombre(String nombreProyecto) {
	    return repoProyectoBanco.buscarProyectosBancoDisponiblesPorNombre(nombreProyecto);
	}
	
	@Override
	public List<ProyectoBanco> buscarProyectosDisponiblesParaResidente(String nombreProyecto) {
	    return repoProyectoBanco.buscarProyectosDisponiblesParaResidente(nombreProyecto);
	}
	//prueba
	@Override
	public List<ProyectoBanco> buscarProyectosActivosPorNombre(String nombreProyecto) {
	    if (nombreProyecto != null && !nombreProyecto.isBlank()) {
	        return repoProyectoBanco.findByNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
	    }

	    return repoProyectoBanco.findByEstatus(1);
	}
}
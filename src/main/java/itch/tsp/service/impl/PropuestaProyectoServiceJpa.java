package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.PropuestaProyecto;
import itch.tsp.repository.PropuestaProyectoRepository;
import itch.tsp.service.IPropuestaProyectoService;

@Primary
@Service
public class PropuestaProyectoServiceJpa implements IPropuestaProyectoService {

	@Autowired
	private PropuestaProyectoRepository repoPropuestaProyecto;

	@Override
	public List<PropuestaProyecto> buscarTodasProp() {
		return repoPropuestaProyecto.findAll();
	}

	@Override
	public List<PropuestaProyecto> buscarPropuestasActivas(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.trim().isEmpty();
		boolean tienePeriodo = periodo != null && !periodo.trim().isEmpty();

		if (tieneNombre && tienePeriodo) {
			return repoPropuestaProyecto.findAll().stream()
					.filter(p -> p.getEstatus() != null && p.getEstatus() == 1)
					.filter(p -> p.getNombreProyecto() != null
							&& p.getNombreProyecto().toLowerCase().contains(nombreProyecto.toLowerCase()))
					.filter(p -> p.getPeriodo() != null
							&& p.getPeriodo().toLowerCase().contains(periodo.toLowerCase()))
					.toList();
		}

		if (tieneNombre) {
			return repoPropuestaProyecto.findByNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
		}

		if (tienePeriodo) {
			return repoPropuestaProyecto.findByPeriodoContainingIgnoreCaseAndEstatus(periodo, 1);
		}

		return repoPropuestaProyecto.findByEstatus(1);
	}

	@Override
	public List<PropuestaProyecto> buscarTodasPropuestas(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.trim().isEmpty();
		boolean tienePeriodo = periodo != null && !periodo.trim().isEmpty();

		if (tieneNombre && tienePeriodo) {
			return repoPropuestaProyecto.findAll().stream()
					.filter(p -> p.getNombreProyecto() != null
							&& p.getNombreProyecto().toLowerCase().contains(nombreProyecto.toLowerCase()))
					.filter(p -> p.getPeriodo() != null
							&& p.getPeriodo().toLowerCase().contains(periodo.toLowerCase()))
					.toList();
		}

		if (tieneNombre) {
			return repoPropuestaProyecto.findByNombreProyectoContainingIgnoreCase(nombreProyecto);
		}

		if (tienePeriodo) {
			return repoPropuestaProyecto.findByPeriodoContainingIgnoreCase(periodo);
		}

		return repoPropuestaProyecto.findAll();
	}

	@Override
	public List<PropuestaProyecto> buscarTodasIncluyendoInactivas() {
		return repoPropuestaProyecto.findAll();
	}

	@Override
	public void guardarPropuestaProyecto(PropuestaProyecto propuestaProyecto) {
		repoPropuestaProyecto.save(propuestaProyecto);
	}

	@Override
	public PropuestaProyecto buscarPorIdProp(Integer id) {
		Optional<PropuestaProyecto> optional = repoPropuestaProyecto.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void eliminarPropuestaProyecto(Integer id) {
		PropuestaProyecto propuestaProyecto = repoPropuestaProyecto.findById(id).orElse(null);

		if (propuestaProyecto != null) {
			propuestaProyecto.setEstatus(0);
			repoPropuestaProyecto.save(propuestaProyecto);
		}
	}

	@Override
	public void activarPropuestaProyecto(Integer id) {
		PropuestaProyecto propuestaProyecto = repoPropuestaProyecto.findById(id).orElse(null);

		if (propuestaProyecto != null) {
			propuestaProyecto.setEstatus(1);
			repoPropuestaProyecto.save(propuestaProyecto);
		}
	}

	@Override
	public List<PropuestaProyecto> buscarPropuestasParaRevision(String estadoRevision) {
		if (estadoRevision != null && !estadoRevision.trim().isEmpty()) {
			return repoPropuestaProyecto.findByEstadoRevisionAndEstatus(estadoRevision, 1);
		}
		return repoPropuestaProyecto.findByEstatus(1);
	}

	@Override
	public void actualizarRevisionPropuesta(PropuestaProyecto propuestaProyecto) {
		repoPropuestaProyecto.save(propuestaProyecto);
	}

	@Override
	public List<PropuestaProyecto> buscarPropuestasDisponibles() {
		return repoPropuestaProyecto.buscarPropuestasDisponibles();
	}

	@Override
	public List<PropuestaProyecto> buscarTodasPropuestas(String nombreProyecto) {
		if (nombreProyecto != null && !nombreProyecto.trim().isEmpty()) {
			return repoPropuestaProyecto.findByNombreProyectoContainingIgnoreCase(nombreProyecto);
		}
		return repoPropuestaProyecto.findAll();
	}

	@Override
	public List<PropuestaProyecto> buscarPorResidente(Integer idResidente) {
		return repoPropuestaProyecto.findAll().stream()
				.filter(p -> p.getResidente() != null
						&& p.getResidente().getIdResidente() != null
						&& p.getResidente().getIdResidente().equals(idResidente))
				.filter(p -> p.getEstatus() != null && p.getEstatus() == 1)
				.toList();
	}

	@Override
	public PropuestaProyecto buscarPorIdPropuesta(Integer id) {
		Optional<PropuestaProyecto> optional = repoPropuestaProyecto.findById(id);
		return optional.orElse(null);
	}
}
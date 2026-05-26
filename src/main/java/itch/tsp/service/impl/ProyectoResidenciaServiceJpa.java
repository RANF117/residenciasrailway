package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.ProyectoResidencia;
import itch.tsp.repository.DocumentoResidenciaRepository;
import itch.tsp.repository.ProyectoResidenciaRepository;
import itch.tsp.repository.ReportePreliminarRepository;
import itch.tsp.repository.SeguimientoResidenciaRepository;
import itch.tsp.service.IProyectoResidenciaService;

@Primary
@Service
public class ProyectoResidenciaServiceJpa implements IProyectoResidenciaService {

	@Autowired
	private ProyectoResidenciaRepository repoProyectoResidencia;

	@Autowired
	private DocumentoResidenciaRepository repoDocumentoResidencia;

	@Autowired
	private ReportePreliminarRepository repoReportePreliminar;

	@Autowired
	private SeguimientoResidenciaRepository repoSeguimientoResidencia;

	@Override
	public List<ProyectoResidencia> buscarTodosProyRes() {
		return repoProyectoResidencia.findByEstatus(1);
	}

	@Override
	public List<ProyectoResidencia> buscarProyectosResidenciaActivos(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tienePeriodo = periodo != null && !periodo.isBlank();

		if (tieneNombre) {
			return repoProyectoResidencia.findByNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
		}

		if (tienePeriodo) {
		    return repoProyectoResidencia.findByPeriodoContainingIgnoreCaseAndEstatus(periodo, 1);
		}
		return repoProyectoResidencia.findByEstatus(1);
	}

	@Override
	public List<ProyectoResidencia> buscarTodosProyectosResidencia(String nombreProyecto, String periodo) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tienePeriodo = periodo != null && !periodo.isBlank();

		if (tieneNombre) {
			return repoProyectoResidencia.findByNombreProyectoContainingIgnoreCase(nombreProyecto);
		}

		if (tienePeriodo) {
		    return repoProyectoResidencia.findByPeriodoContainingIgnoreCase(periodo);
		}

		return repoProyectoResidencia.findAll();
	}

	@Override
	public List<ProyectoResidencia> buscarTodosIncluyendoInactivos() {
		return repoProyectoResidencia.findAll();
	}

	@Override
	public void guardarProyectoResidencia(ProyectoResidencia proyectoResidencia) {
		repoProyectoResidencia.save(proyectoResidencia);
	}

	@Override
	public ProyectoResidencia buscarPorIdProyRes(Integer id) {
		Optional<ProyectoResidencia> optional = repoProyectoResidencia.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarProyectoResidencia(Integer id) {
		ProyectoResidencia proyectoResidencia = repoProyectoResidencia.findById(id).orElse(null);

		if (proyectoResidencia != null) {
			proyectoResidencia.setEstatus(0);
			repoProyectoResidencia.save(proyectoResidencia);
		}
	}

	@Override
	public void activarProyectoResidencia(Integer id) {
		ProyectoResidencia proyectoResidencia = repoProyectoResidencia.findById(id).orElse(null);

		if (proyectoResidencia != null) {
			proyectoResidencia.setEstatus(1);
			repoProyectoResidencia.save(proyectoResidencia);
		}
	}

	@Override
	public boolean existePorProyectoBanco(Integer idProyectoBanco) {
		return repoProyectoResidencia.existsByProyectoBanco_IdProyectoBanco(idProyectoBanco);
	}

	@Override
	public boolean existePorPropuestaProyecto(Integer idPropuestaProyecto) {
		return repoProyectoResidencia.existsByPropuestaProyecto_IdPropuestaProyecto(idPropuestaProyecto);
	}

	@Override
	public boolean proyectoResidenciaTieneVinculosActivos(Integer id) {
		boolean tieneDocumentos = repoDocumentoResidencia.existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(id, 1);
		boolean tieneReportePreliminar = repoReportePreliminar.existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(id, 1);
		boolean tieneSeguimientos = repoSeguimientoResidencia.existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(id, 1);

		return tieneDocumentos || tieneReportePreliminar || tieneSeguimientos;
	}
}
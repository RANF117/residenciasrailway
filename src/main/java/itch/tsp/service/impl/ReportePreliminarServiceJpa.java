package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.ReportePreliminar;
import itch.tsp.repository.ReportePreliminarRepository;
import itch.tsp.service.IReportePreliminarService;

@Primary
@Service
public class ReportePreliminarServiceJpa implements IReportePreliminarService {

	@Autowired
	private ReportePreliminarRepository repoReportePreliminar;

	@Override
	public List<ReportePreliminar> buscarTodosRepPre() {
		return repoReportePreliminar.findByEstatus(1);
	}

	@Override
	public List<ReportePreliminar> buscarReportesActivos(String nombreProyecto, String dictamen) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tieneDictamen = dictamen != null && !dictamen.isBlank();

		if (tieneNombre) {
			return repoReportePreliminar.findByProyectoResidenciaNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
		}

		if (tieneDictamen) {
			return repoReportePreliminar.findByDictamenAndEstatus(dictamen, 1);
		}

		return repoReportePreliminar.findByEstatus(1);
	}

	@Override
	public List<ReportePreliminar> buscarTodosReportes(String nombreProyecto, String dictamen) {
		boolean tieneNombre = nombreProyecto != null && !nombreProyecto.isBlank();
		boolean tieneDictamen = dictamen != null && !dictamen.isBlank();

		if (tieneNombre) {
			return repoReportePreliminar.findByProyectoResidenciaNombreProyectoContainingIgnoreCase(nombreProyecto);
		}

		if (tieneDictamen) {
			return repoReportePreliminar.findByDictamen(dictamen);
		}

		return repoReportePreliminar.findAll();
	}

	@Override
	public List<ReportePreliminar> buscarTodosIncluyendoInactivos() {
		return repoReportePreliminar.findAll();
	}

	@Override
	public List<ReportePreliminar> buscarPorProyectoResidencia(Integer idProyectoResidencia) {
		return repoReportePreliminar.findByProyectoResidenciaIdProyectoResidenciaAndEstatus(idProyectoResidencia, 1);
	}

	@Override
	public void guardarReportePreliminar(ReportePreliminar reportePreliminar) {
		repoReportePreliminar.save(reportePreliminar);
	}

	@Override
	public ReportePreliminar buscarPorIdRepPre(Integer id) {
		Optional<ReportePreliminar> optional = repoReportePreliminar.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarReportePreliminar(Integer id) {
		ReportePreliminar reportePreliminar = repoReportePreliminar.findById(id).orElse(null);

		if (reportePreliminar != null) {
			reportePreliminar.setEstatus(0);
			repoReportePreliminar.save(reportePreliminar);
		}
	}

	@Override
	public void activarReportePreliminar(Integer id) {
		ReportePreliminar reportePreliminar = repoReportePreliminar.findById(id).orElse(null);

		if (reportePreliminar != null) {
			reportePreliminar.setEstatus(1);
			repoReportePreliminar.save(reportePreliminar);
		}
	}
}
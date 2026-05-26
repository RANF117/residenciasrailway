package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.EvaluacionReporteFinal;
import itch.tsp.repository.EvaluacionReporteFinalRepository;
import itch.tsp.service.IEvaluacionReporteFinalService;

@Primary
@Service
public class EvaluacionReporteFinalServiceJpa implements IEvaluacionReporteFinalService {

	@Autowired
	private EvaluacionReporteFinalRepository repoEvaluacionReporteFinal;

	@Override
	public List<EvaluacionReporteFinal> buscarEvaluacionesActivas(String nombreProyecto) {
		if (nombreProyecto != null && !nombreProyecto.isBlank()) {
			return repoEvaluacionReporteFinal.findByProyectoResidenciaNombreProyectoContainingIgnoreCaseAndEstatus(nombreProyecto, 1);
		}
		return repoEvaluacionReporteFinal.findByEstatus(1);
	}

	@Override
	public List<EvaluacionReporteFinal> buscarTodasEvaluaciones(String nombreProyecto) {
		if (nombreProyecto != null && !nombreProyecto.isBlank()) {
			return repoEvaluacionReporteFinal.findByProyectoResidenciaNombreProyectoContainingIgnoreCase(nombreProyecto);
		}
		return repoEvaluacionReporteFinal.findAll();
	}

	@Override
	public EvaluacionReporteFinal buscarPorId(Integer id) {
		Optional<EvaluacionReporteFinal> optional = repoEvaluacionReporteFinal.findById(id);
		return optional.orElse(null);
	}

	@Override
	public EvaluacionReporteFinal buscarPorProyecto(Integer idProyectoResidencia) {
		return repoEvaluacionReporteFinal.findByProyectoResidenciaIdProyectoResidenciaAndEstatus(idProyectoResidencia, 1);
	}

	@Override
	public void guardar(EvaluacionReporteFinal evaluacionReporteFinal) {
		repoEvaluacionReporteFinal.save(evaluacionReporteFinal);
	}

	@Override
	public void eliminar(Integer id) {
		EvaluacionReporteFinal evaluacion = repoEvaluacionReporteFinal.findById(id).orElse(null);
		if (evaluacion != null) {
			evaluacion.setEstatus(0);
			repoEvaluacionReporteFinal.save(evaluacion);
		}
	}

	@Override
	public void activar(Integer id) {
		EvaluacionReporteFinal evaluacion = repoEvaluacionReporteFinal.findById(id).orElse(null);
		if (evaluacion != null) {
			evaluacion.setEstatus(1);
			repoEvaluacionReporteFinal.save(evaluacion);
		}
	}
}
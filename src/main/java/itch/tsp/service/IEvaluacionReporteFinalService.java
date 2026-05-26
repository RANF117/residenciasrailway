package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.EvaluacionReporteFinal;

public interface IEvaluacionReporteFinalService {

	List<EvaluacionReporteFinal> buscarEvaluacionesActivas(String nombreProyecto);

	List<EvaluacionReporteFinal> buscarTodasEvaluaciones(String nombreProyecto);

	EvaluacionReporteFinal buscarPorId(Integer id);

	EvaluacionReporteFinal buscarPorProyecto(Integer idProyectoResidencia);

	void guardar(EvaluacionReporteFinal evaluacionReporteFinal);

	void eliminar(Integer id);

	void activar(Integer id);
}
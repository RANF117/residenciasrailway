package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.ReportePreliminar;

public interface IReportePreliminarService {

	List<ReportePreliminar> buscarTodosRepPre();

	List<ReportePreliminar> buscarReportesActivos(String nombreProyecto, String dictamen);

	List<ReportePreliminar> buscarTodosReportes(String nombreProyecto, String dictamen);

	List<ReportePreliminar> buscarTodosIncluyendoInactivos();

	List<ReportePreliminar> buscarPorProyectoResidencia(Integer idProyectoResidencia);

	void guardarReportePreliminar(ReportePreliminar reportePreliminar);

	ReportePreliminar buscarPorIdRepPre(Integer id);

	void eliminarReportePreliminar(Integer id);

	void activarReportePreliminar(Integer id);
}
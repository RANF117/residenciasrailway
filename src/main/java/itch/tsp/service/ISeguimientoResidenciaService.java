package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.SeguimientoResidencia;

public interface ISeguimientoResidenciaService {

	List<SeguimientoResidencia> buscarTodosSeg();

	List<SeguimientoResidencia> buscarSeguimientosActivos(String tipoSeguimiento);

	List<SeguimientoResidencia> buscarTodosSeguimientos(String tipoSeguimiento);

	List<SeguimientoResidencia> buscarTodosIncluyendoInactivos();

	List<SeguimientoResidencia> buscarPorProyectoResidencia(Integer idProyectoResidencia);

	SeguimientoResidencia buscarPorProyectoYTipo(Integer idProyectoResidencia, String tipoSeguimiento);

	void guardarSeguimientoResidencia(SeguimientoResidencia seguimientoResidencia);

	SeguimientoResidencia buscarPorIdSeg(Integer id);

	void eliminarSeguimientoResidencia(Integer id);

	void activarSeguimientoResidencia(Integer id);
}
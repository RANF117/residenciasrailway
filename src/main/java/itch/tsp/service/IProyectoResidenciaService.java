package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.ProyectoResidencia;

public interface IProyectoResidenciaService {

	List<ProyectoResidencia> buscarTodosProyRes();

	List<ProyectoResidencia> buscarProyectosResidenciaActivos(String nombreProyecto, String periodo);

	List<ProyectoResidencia> buscarTodosProyectosResidencia(String nombreProyecto, String periodo);

	List<ProyectoResidencia> buscarTodosIncluyendoInactivos();

	void guardarProyectoResidencia(ProyectoResidencia proyectoResidencia);

	ProyectoResidencia buscarPorIdProyRes(Integer id);

	void eliminarProyectoResidencia(Integer id);

	void activarProyectoResidencia(Integer id);

	boolean existePorProyectoBanco(Integer idProyectoBanco);

	boolean existePorPropuestaProyecto(Integer idPropuestaProyecto);

	boolean proyectoResidenciaTieneVinculosActivos(Integer id);
}
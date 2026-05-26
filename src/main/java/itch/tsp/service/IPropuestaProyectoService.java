package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.PropuestaProyecto;

public interface IPropuestaProyectoService {

	List<PropuestaProyecto> buscarTodasProp();

	List<PropuestaProyecto> buscarPropuestasActivas(String nombreProyecto, String periodo);

	List<PropuestaProyecto> buscarTodasPropuestas(String nombreProyecto, String periodo);

	List<PropuestaProyecto> buscarTodasIncluyendoInactivas();

	void guardarPropuestaProyecto(PropuestaProyecto propuestaProyecto);

	PropuestaProyecto buscarPorIdProp(Integer id);

	void eliminarPropuestaProyecto(Integer id);

	void activarPropuestaProyecto(Integer id);
	
	List<PropuestaProyecto> buscarPropuestasParaRevision(String estadoRevision);

	void actualizarRevisionPropuesta(PropuestaProyecto propuestaProyecto);
	
	List<PropuestaProyecto> buscarPropuestasDisponibles();
	List<PropuestaProyecto> buscarTodasPropuestas(String nombreProyecto);

	List<PropuestaProyecto> buscarPorResidente(Integer idResidente);

	PropuestaProyecto buscarPorIdPropuesta(Integer id);
}
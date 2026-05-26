package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.ProyectoBanco;

public interface IProyectoBancoService {

	List<ProyectoBanco> buscarTodosProyBan();

	List<ProyectoBanco> buscarProyectosBancoActivos(String nombreProyecto, String periodo);

	List<ProyectoBanco> buscarTodosProyectosBanco(String nombreProyecto, String periodo);

	List<ProyectoBanco> buscarTodosIncluyendoInactivos();

	void guardarProyectoBanco(ProyectoBanco proyectoBanco);

	ProyectoBanco buscarPorIdProyBan(Integer id);

	void eliminarProyectoBanco(Integer id);

	void activarProyectoBanco(Integer id);
	
	List<ProyectoBanco> buscarProyectosBancoDisponibles();
	
	List<ProyectoBanco> buscarProyectosBancoDisponiblesPorNombre(String nombreProyecto);
	
	List<ProyectoBanco> buscarProyectosDisponiblesParaResidente(String nombreProyecto);
	
	//prueba
	List<ProyectoBanco> buscarProyectosActivosPorNombre(String nombreProyecto);
}
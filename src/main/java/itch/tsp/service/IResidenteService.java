package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.Residente;

public interface IResidenteService {

	List<Residente> buscarTodosRes();

	List<Residente> buscarResidentesActivosPorNombre(String nombre);

	List<Residente> buscarTodosResidentesPorNombre(String nombre);

	List<Residente> buscarTodosIncluyendoInactivos();

	void guardarResidente(Residente residente);

	Residente buscarPorIdRes(Integer id);

	void eliminarResidente(Integer id);

	void activarResidente(Integer id);
	
	List<Residente> buscarResidentesConProyectoPorNombre(String nombre);

	
	boolean residenteTieneVinculoActivo(Integer id);
	
	List<Residente> buscarResidentesSinProyectoPorNombre(String nombre);
}
package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.Carrera;

public interface ICarreraService {

	List<Carrera> buscarTodasCar();

	List<Carrera> buscarCarrerasActivasPorNombre(String nombre);

	List<Carrera> buscarTodasCarrerasPorNombre(String nombre);

	List<Carrera> buscarTodasIncluyendoInactivas();

	void guardarCarrera(Carrera carrera);

	Carrera buscarPorIdCar(Integer id);

	void eliminarCarrera(Integer id);

	void activarCarrera(Integer id);

	boolean carreraTieneVinculoActivo(Integer id);
}
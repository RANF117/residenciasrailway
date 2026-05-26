package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.AsesorInterno;

public interface IAsesorInternoService {

	List<AsesorInterno> buscarTodosAsInt();

	List<AsesorInterno> buscarAsesoresInternosActivosPorNombre(String nombre);

	List<AsesorInterno> buscarTodosAsesoresInternosPorNombre(String nombre);

	List<AsesorInterno> buscarTodosIncluyendoInactivos();

	void guardarAsesorInterno(AsesorInterno asesorInterno);

	AsesorInterno buscarPorIdAsInt(Integer id);

	void eliminarAsesorInterno(Integer id);

	void activarAsesorInterno(Integer id);
	
	boolean asesorInternoTieneVinculoActivo(Integer id);
}
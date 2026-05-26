package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.AsesorExterno;

public interface IAsesorExternoService {

	List<AsesorExterno> buscarTodosAsExt();

	List<AsesorExterno> buscarAsesoresExternosActivosPorNombre(String nombre);

	List<AsesorExterno> buscarTodosAsesoresExternosPorNombre(String nombre);

	List<AsesorExterno> buscarTodosIncluyendoInactivos();

	void guardarAsesorExterno(AsesorExterno asesorExterno);

	AsesorExterno buscarPorIdAsExt(Integer id);

	void eliminarAsesorExterno(Integer id);

	void activarAsesorExterno(Integer id);
	
	boolean asesorExternoTieneVinculoActivo(Integer id);
}
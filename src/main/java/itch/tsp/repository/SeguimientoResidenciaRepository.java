package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.SeguimientoResidencia;

public interface SeguimientoResidenciaRepository extends JpaRepository<SeguimientoResidencia, Integer> {

	List<SeguimientoResidencia> findByEstatus(Integer estatus);

	List<SeguimientoResidencia> findByTipoSeguimientoAndEstatus(String tipoSeguimiento, Integer estatus);

	List<SeguimientoResidencia> findByTipoSeguimiento(String tipoSeguimiento);

	List<SeguimientoResidencia> findByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);

	SeguimientoResidencia findByProyectoResidenciaIdProyectoResidenciaAndTipoSeguimientoAndEstatus(
			Integer idProyectoResidencia, String tipoSeguimiento, Integer estatus
	);

	boolean existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);
}
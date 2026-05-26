package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.ReportePreliminar;

public interface ReportePreliminarRepository extends JpaRepository<ReportePreliminar, Integer> {

	List<ReportePreliminar> findByEstatus(Integer estatus);

	List<ReportePreliminar> findByProyectoResidenciaNombreProyectoContainingIgnoreCaseAndEstatus(String nombreProyecto, Integer estatus);

	List<ReportePreliminar> findByProyectoResidenciaNombreProyectoContainingIgnoreCase(String nombreProyecto);

	List<ReportePreliminar> findByDictamenAndEstatus(String dictamen, Integer estatus);

	List<ReportePreliminar> findByDictamen(String dictamen);

	List<ReportePreliminar> findByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);

	boolean existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);
}
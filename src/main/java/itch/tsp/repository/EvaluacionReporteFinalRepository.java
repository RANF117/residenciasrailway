package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.EvaluacionReporteFinal;

public interface EvaluacionReporteFinalRepository extends JpaRepository<EvaluacionReporteFinal, Integer> {

	List<EvaluacionReporteFinal> findByEstatus(Integer estatus);

	List<EvaluacionReporteFinal> findByProyectoResidenciaNombreProyectoContainingIgnoreCaseAndEstatus(String nombreProyecto, Integer estatus);

	List<EvaluacionReporteFinal> findByProyectoResidenciaNombreProyectoContainingIgnoreCase(String nombreProyecto);

	EvaluacionReporteFinal findByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);

	boolean existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);
}
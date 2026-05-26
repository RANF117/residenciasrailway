package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.DocumentoResidencia;

public interface DocumentoResidenciaRepository extends JpaRepository<DocumentoResidencia, Integer> {

	List<DocumentoResidencia> findByEstatus(Integer estatus);

	List<DocumentoResidencia> findByTipoDocumentoAndEstatus(String tipoDocumento, Integer estatus);

	List<DocumentoResidencia> findByTipoDocumento(String tipoDocumento);

	List<DocumentoResidencia> findByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);

	List<DocumentoResidencia> findByProyectoResidenciaIdProyectoResidencia(Integer idProyectoResidencia);

	boolean existsByProyectoResidenciaIdProyectoResidenciaAndEstatus(Integer idProyectoResidencia, Integer estatus);

	DocumentoResidencia findByProyectoResidenciaIdProyectoResidenciaAndTipoDocumentoAndEstatus(
			Integer idProyectoResidencia,
			String tipoDocumento,
			Integer estatus
	);
}
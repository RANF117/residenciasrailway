package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import itch.tsp.modelo.PropuestaProyecto;

public interface PropuestaProyectoRepository extends JpaRepository<PropuestaProyecto, Integer> {

	List<PropuestaProyecto> findByEstatus(Integer estatus);

	List<PropuestaProyecto> findByNombreProyectoContainingIgnoreCaseAndEstatus(String nombreProyecto, Integer estatus);

	List<PropuestaProyecto> findByNombreProyectoContainingIgnoreCase(String nombreProyecto);
	
	List<PropuestaProyecto> findByEstadoRevision(String estadoRevision);

	List<PropuestaProyecto> findByEstadoRevisionAndEstatus(String estadoRevision, Integer estatus);
	
	@Query("""
		    SELECT pp FROM PropuestaProyecto pp
		    WHERE pp.estadoRevision = 'APROBADA'
		    AND pp.idPropuestaProyecto NOT IN (
		        SELECT pr.propuestaProyecto.idPropuestaProyecto
		        FROM ProyectoResidencia pr
		        WHERE pr.propuestaProyecto IS NOT NULL
		        AND pr.estatus = 1
		    )
		""")
	List<PropuestaProyecto> buscarPropuestasDisponibles();
	
	boolean existsByResidente_IdResidenteAndEstatus(Integer idResidente, Integer estatus);

	boolean existsByEmpresa_IdEmpresaAndEstatus(Integer idEmpresa, Integer estatus);
	
	List<PropuestaProyecto> findByPeriodoContainingIgnoreCaseAndEstatus(String periodo, Integer estatus);

	List<PropuestaProyecto> findByPeriodoContainingIgnoreCase(String periodo);
}
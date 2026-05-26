package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import itch.tsp.modelo.Residente;

public interface ResidenteRepository extends JpaRepository<Residente, Integer> {

	List<Residente> findByEstatus(Integer estatus);

	List<Residente> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<Residente> findByNombreContainingIgnoreCase(String nombre);

	boolean existsByCarrera_IdCarreraAndEstatus(Integer idCarrera, Integer estatus);

	Residente findByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);
	boolean existsByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);

	@Query("""
	    SELECT DISTINCT r
	    FROM ProyectoResidencia pr
	    JOIN pr.residente r
	    WHERE r.estatus = 1
	    AND pr.estatus = 1
	    AND pr.residente IS NOT NULL
	    AND (
	        :nombre IS NULL
	        OR :nombre = ''
	        OR LOWER(CONCAT(r.nombre, ' ', r.apellidoPaterno, ' ', r.apellidoMaterno))
	           LIKE LOWER(CONCAT('%', :nombre, '%'))
	    )
	""")
	List<Residente> buscarResidentesConProyectoPorNombre(@Param("nombre") String nombre);

	@Query("""
	    SELECT r
	    FROM Residente r
	    WHERE r.estatus = 1
	    AND r.idResidente NOT IN (
	        SELECT pr.residente.idResidente
	        FROM ProyectoResidencia pr
	        WHERE pr.residente IS NOT NULL
	        AND pr.estatus = 1
	    )
	    AND (
	        :nombre IS NULL
	        OR :nombre = ''
	        OR LOWER(CONCAT(r.nombre, ' ', r.apellidoPaterno, ' ', r.apellidoMaterno))
	           LIKE LOWER(CONCAT('%', :nombre, '%'))
	    )
	""")
	List<Residente> buscarResidentesSinProyectoPorNombre(@Param("nombre") String nombre);
}
package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import itch.tsp.modelo.ProyectoBanco;

public interface ProyectoBancoRepository extends JpaRepository<ProyectoBanco, Integer> {

	List<ProyectoBanco> findByEstatus(Integer estatus);

	List<ProyectoBanco> findByNombreProyectoContainingIgnoreCaseAndEstatus(String nombreProyecto, Integer estatus);

	List<ProyectoBanco> findByNombreProyectoContainingIgnoreCase(String nombreProyecto);
	
	@Query("""
			SELECT pb FROM ProyectoBanco pb
			WHERE pb.estatus = 1
			AND pb.idProyectoBanco NOT IN (
				SELECT pr.proyectoBanco.idProyectoBanco
				FROM ProyectoResidencia pr
				WHERE pr.proyectoBanco IS NOT NULL
				AND pr.estatus = 1
			)
		""")
		List<ProyectoBanco> buscarProyectosBancoDisponibles();
	
	@Query("""
		    SELECT pb
		    FROM ProyectoBanco pb
		    WHERE pb.estatus = 1
		    AND pb.idProyectoBanco NOT IN (
		        SELECT pr.proyectoBanco.idProyectoBanco
		        FROM ProyectoResidencia pr
		        WHERE pr.proyectoBanco IS NOT NULL
		        AND pr.estatus = 1
		    )
		    AND (
		        :nombreProyecto IS NULL
		        OR :nombreProyecto = ''
		        OR LOWER(pb.nombreProyecto) LIKE LOWER(CONCAT('%', :nombreProyecto, '%'))
		    )
		""")
		List<ProyectoBanco> buscarProyectosBancoDisponiblesPorNombre(@Param("nombreProyecto") String nombreProyecto);
	@Query("""
		    SELECT pb
		    FROM ProyectoBanco pb
		    WHERE pb.estatus = 1
		      AND (:nombreProyecto IS NULL OR :nombreProyecto = ''
		           OR LOWER(pb.nombreProyecto) LIKE LOWER(CONCAT('%', :nombreProyecto, '%')))
		      AND pb.idProyectoBanco NOT IN (
		          SELECT pr.proyectoBanco.idProyectoBanco
		          FROM ProyectoResidencia pr
		          WHERE pr.estatus = 1
		            AND pr.proyectoBanco IS NOT NULL
		      )
		""")
		List<ProyectoBanco> buscarProyectosDisponiblesParaResidente(@Param("nombreProyecto") String nombreProyecto);
	
	List<ProyectoBanco> findByPeriodoContainingIgnoreCaseAndEstatus(String periodo, Integer estatus);

	List<ProyectoBanco> findByPeriodoContainingIgnoreCase(String periodo);
	}


package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import itch.tsp.modelo.AsesorExterno;

public interface AsesorExternoRepository extends JpaRepository<AsesorExterno, Integer> {

	List<AsesorExterno> findByEstatus(Integer estatus);

	@Query("SELECT a FROM AsesorExterno a WHERE UPPER(a.nombre) LIKE UPPER(:nombre) ESCAPE '\\' AND a.estatus = :estatus")
	List<AsesorExterno> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<AsesorExterno> findByNombreContainingIgnoreCase(String nombre);

	boolean existsByEmpresa_IdEmpresaAndEstatus(Integer idEmpresa, Integer estatus);

	AsesorExterno findByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);
	
	boolean existsByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);
}
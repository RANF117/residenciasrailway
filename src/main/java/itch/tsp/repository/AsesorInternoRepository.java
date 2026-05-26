package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.AsesorInterno;

public interface AsesorInternoRepository extends JpaRepository<AsesorInterno, Integer> {

	List<AsesorInterno> findByEstatus(Integer estatus);

	List<AsesorInterno> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<AsesorInterno> findByNombreContainingIgnoreCase(String nombre);

	boolean existsByDepartamentoAcademico_IdDepartamentoAcademicoAndEstatus(Integer idDepartamentoAcademico, Integer estatus);

	AsesorInterno findByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);
	
	boolean existsByUsuario_IdAndEstatus(Integer idUsuario, Integer estatus);
}
package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.DepartamentoAcademico;

public interface DepartamentoAcademicoRepository extends JpaRepository<DepartamentoAcademico, Integer> {

	List<DepartamentoAcademico> findByEstatus(Integer estatus);

	List<DepartamentoAcademico> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<DepartamentoAcademico> findByNombreContainingIgnoreCase(String nombre);
}
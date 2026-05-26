package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

	List<Carrera> findByEstatus(Integer estatus);

	List<Carrera> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<Carrera> findByNombreContainingIgnoreCase(String nombre);
}
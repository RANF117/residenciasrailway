package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	List<Empresa> findByEstatus(Integer estatus);

	List<Empresa> findByNombreContainingIgnoreCaseAndEstatus(String nombre, Integer estatus);

	List<Empresa> findByNombreContainingIgnoreCase(String nombre);
}
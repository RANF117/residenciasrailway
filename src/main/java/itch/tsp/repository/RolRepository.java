package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

	List<Rol> findByEstatus(Integer estatus);

}
package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.Carrera;
import itch.tsp.repository.CarreraRepository;
import itch.tsp.repository.ResidenteRepository;
import itch.tsp.service.ICarreraService;

@Primary
@Service
public class CarreraServiceJpa implements ICarreraService {

	@Autowired
	private CarreraRepository repoCarrera;

	@Autowired
	private ResidenteRepository repoResidente;

	@Override
	public List<Carrera> buscarTodasCar() {
		return repoCarrera.findByEstatus(1);
	}

	@Override
	public List<Carrera> buscarCarrerasActivasPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoCarrera.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoCarrera.findByEstatus(1);
	}

	@Override
	public List<Carrera> buscarTodasCarrerasPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoCarrera.findByNombreContainingIgnoreCase(nombre);
		}
		return repoCarrera.findAll();
	}

	@Override
	public List<Carrera> buscarTodasIncluyendoInactivas() {
		return repoCarrera.findAll();
	}

	@Override
	public void guardarCarrera(Carrera carrera) {
		repoCarrera.save(carrera);
	}

	@Override
	public Carrera buscarPorIdCar(Integer id) {
		Optional<Carrera> optional = repoCarrera.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarCarrera(Integer id) {
		Carrera carrera = repoCarrera.findById(id).orElse(null);

		if (carrera != null) {
			carrera.setEstatus(0);
			repoCarrera.save(carrera);
		}
	}

	@Override
	public void activarCarrera(Integer id) {
		Carrera carrera = repoCarrera.findById(id).orElse(null);

		if (carrera != null) {
			carrera.setEstatus(1);
			repoCarrera.save(carrera);
		}
	}

	@Override
	public boolean carreraTieneVinculoActivo(Integer id) {
		return repoResidente.existsByCarrera_IdCarreraAndEstatus(id, 1);
	}
}
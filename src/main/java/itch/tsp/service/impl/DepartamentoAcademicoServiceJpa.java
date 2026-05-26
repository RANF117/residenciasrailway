package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.DepartamentoAcademico;
import itch.tsp.repository.AsesorInternoRepository;
import itch.tsp.repository.DepartamentoAcademicoRepository;
import itch.tsp.service.IDepartamentoAcademicoService;

@Primary
@Service
public class DepartamentoAcademicoServiceJpa implements IDepartamentoAcademicoService {

	@Autowired
	private DepartamentoAcademicoRepository repoDepartamento;

	@Autowired
	private AsesorInternoRepository repoAsesorInterno;

	@Override
	public List<DepartamentoAcademico> buscarTodosDep() {
		return repoDepartamento.findByEstatus(1);
	}

	@Override
	public List<DepartamentoAcademico> buscarDepartamentosActivosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoDepartamento.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoDepartamento.findByEstatus(1);
	}

	@Override
	public List<DepartamentoAcademico> buscarTodosDepartamentosPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoDepartamento.findByNombreContainingIgnoreCase(nombre);
		}
		return repoDepartamento.findAll();
	}

	@Override
	public List<DepartamentoAcademico> buscarTodosIncluyendoInactivos() {
		return repoDepartamento.findAll();
	}

	@Override
	public void guardarDepartamento(DepartamentoAcademico departamentoAcademico) {
		repoDepartamento.save(departamentoAcademico);
	}

	@Override
	public DepartamentoAcademico buscarPorIdDep(Integer id) {
		Optional<DepartamentoAcademico> optional = repoDepartamento.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarDepartamento(Integer id) {
		DepartamentoAcademico departamento = repoDepartamento.findById(id).orElse(null);

		if (departamento != null) {
			departamento.setEstatus(0);
			repoDepartamento.save(departamento);
		}
	}

	@Override
	public void activarDepartamento(Integer id) {
		DepartamentoAcademico departamento = repoDepartamento.findById(id).orElse(null);

		if (departamento != null) {
			departamento.setEstatus(1);
			repoDepartamento.save(departamento);
		}
	}

	@Override
	public boolean departamentoTieneVinculoActivo(Integer id) {
		return repoAsesorInterno.existsByDepartamentoAcademico_IdDepartamentoAcademicoAndEstatus(id, 1);
	}
}
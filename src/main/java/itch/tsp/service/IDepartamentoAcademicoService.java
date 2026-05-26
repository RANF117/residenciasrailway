package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.DepartamentoAcademico;

public interface IDepartamentoAcademicoService {

	List<DepartamentoAcademico> buscarTodosDep();

	List<DepartamentoAcademico> buscarDepartamentosActivosPorNombre(String nombre);

	List<DepartamentoAcademico> buscarTodosDepartamentosPorNombre(String nombre);

	List<DepartamentoAcademico> buscarTodosIncluyendoInactivos();

	void guardarDepartamento(DepartamentoAcademico departamentoAcademico);

	DepartamentoAcademico buscarPorIdDep(Integer id);

	void eliminarDepartamento(Integer id);

	void activarDepartamento(Integer id);

	boolean departamentoTieneVinculoActivo(Integer id);
}
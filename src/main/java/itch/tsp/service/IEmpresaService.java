package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.Empresa;

public interface IEmpresaService {

	List<Empresa> buscarTodasEmp();

	List<Empresa> buscarEmpresasActivasPorNombre(String nombre);

	List<Empresa> buscarTodasEmpresasPorNombre(String nombre);

	List<Empresa> buscarTodasIncluyendoInactivas();

	void guardarEmpresa(Empresa empresa);

	Empresa buscarPorIdEmp(Integer id);

	void eliminarEmpresa(Integer id);

	void activarEmpresa(Integer id);

	boolean empresaTieneVinculoActivo(Integer id);
}
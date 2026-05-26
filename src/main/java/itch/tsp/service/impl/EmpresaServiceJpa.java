package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.Empresa;
import itch.tsp.repository.AsesorExternoRepository;
import itch.tsp.repository.EmpresaRepository;
import itch.tsp.repository.PropuestaProyectoRepository;
import itch.tsp.repository.ProyectoResidenciaRepository;
import itch.tsp.service.IEmpresaService;

@Primary
@Service
public class EmpresaServiceJpa implements IEmpresaService {

	@Autowired
	private EmpresaRepository repoEmpresa;

	@Autowired
	private ProyectoResidenciaRepository repoProyectoResidencia;

	@Autowired
	private PropuestaProyectoRepository repoPropuestaProyecto;

	@Autowired
	private AsesorExternoRepository repoAsesorExterno;

	@Override
	public List<Empresa> buscarTodasEmp() {
		return repoEmpresa.findByEstatus(1);
	}

	@Override
	public List<Empresa> buscarEmpresasActivasPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoEmpresa.findByNombreContainingIgnoreCaseAndEstatus(nombre, 1);
		}
		return repoEmpresa.findByEstatus(1);
	}

	@Override
	public List<Empresa> buscarTodasEmpresasPorNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			return repoEmpresa.findByNombreContainingIgnoreCase(nombre);
		}
		return repoEmpresa.findAll();
	}

	@Override
	public List<Empresa> buscarTodasIncluyendoInactivas() {
		return repoEmpresa.findAll();
	}

	@Override
	public void guardarEmpresa(Empresa empresa) {
		repoEmpresa.save(empresa);
	}

	@Override
	public Empresa buscarPorIdEmp(Integer id) {
		Optional<Empresa> optional = repoEmpresa.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarEmpresa(Integer id) {
		Empresa empresa = repoEmpresa.findById(id).orElse(null);

		if (empresa != null) {
			empresa.setEstatus(0);
			repoEmpresa.save(empresa);
		}
	}

	@Override
	public void activarEmpresa(Integer id) {
		Empresa empresa = repoEmpresa.findById(id).orElse(null);

		if (empresa != null) {
			empresa.setEstatus(1);
			repoEmpresa.save(empresa);
		}
	}

	@Override
	public boolean empresaTieneVinculoActivo(Integer id) {
		boolean tieneProyectoActivo = repoProyectoResidencia.existsByEmpresa_IdEmpresaAndEstatus(id, 1);
		boolean tienePropuestaActiva = repoPropuestaProyecto.existsByEmpresa_IdEmpresaAndEstatus(id, 1);
		boolean tieneAsesorExternoActivo = repoAsesorExterno.existsByEmpresa_IdEmpresaAndEstatus(id, 1);

		return tieneProyectoActivo || tienePropuestaActiva || tieneAsesorExternoActivo;
	}
}
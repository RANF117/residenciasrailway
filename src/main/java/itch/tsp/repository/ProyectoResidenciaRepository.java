package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.modelo.ProyectoResidencia;

public interface ProyectoResidenciaRepository extends JpaRepository<ProyectoResidencia, Integer> {

	List<ProyectoResidencia> findByEstatus(Integer estatus);

	List<ProyectoResidencia> findByNombreProyectoContainingIgnoreCaseAndEstatus(String nombreProyecto, Integer estatus);

	List<ProyectoResidencia> findByNombreProyectoContainingIgnoreCase(String nombreProyecto);
	
	//busquedas por periodo 
	
	List<ProyectoResidencia> findByPeriodoContainingIgnoreCaseAndEstatus(String periodo, Integer estatus);

	List<ProyectoResidencia> findByPeriodoContainingIgnoreCase(String periodo);
	
	boolean existsByProyectoBanco_IdProyectoBanco(Integer idProyectoBanco);

    boolean existsByPropuestaProyecto_IdPropuestaProyecto(Integer idPropuestaProyecto);
    
    boolean existsByResidente_IdResidenteAndEstatus(Integer idResidente, Integer estatus);

    boolean existsByEmpresa_IdEmpresaAndEstatus(Integer idEmpresa, Integer estatus);

    boolean existsByAsesorExterno_IdAsesorExternoAndEstatus(Integer idAsesorExterno, Integer estatus);

    boolean existsByAsesorInterno_IdAsesorInternoAndEstatus(Integer idAsesorInterno, Integer estatus);

}
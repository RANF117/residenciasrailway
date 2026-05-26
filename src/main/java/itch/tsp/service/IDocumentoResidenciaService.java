package itch.tsp.service;

import java.util.List;

import itch.tsp.modelo.DocumentoResidencia;

public interface IDocumentoResidenciaService {

	List<DocumentoResidencia> buscarTodosDocRes();

	List<DocumentoResidencia> buscarDocumentosActivos(String tipoDocumento);

	List<DocumentoResidencia> buscarTodosDocumentos(String tipoDocumento);

	List<DocumentoResidencia> buscarTodosIncluyendoInactivos();

	List<DocumentoResidencia> buscarPorProyectoResidencia(Integer idProyectoResidencia);

	DocumentoResidencia buscarPorProyectoYTipo(Integer idProyectoResidencia, String tipoDocumento);

	void guardarDocumentoResidencia(DocumentoResidencia documentoResidencia);

	DocumentoResidencia buscarPorIdDocRes(Integer id);

	void eliminarDocumentoResidencia(Integer id);

	void activarDocumentoResidencia(Integer id);
	
}
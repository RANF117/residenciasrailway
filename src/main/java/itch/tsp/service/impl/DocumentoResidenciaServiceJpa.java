package itch.tsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.modelo.DocumentoResidencia;
import itch.tsp.repository.DocumentoResidenciaRepository;
import itch.tsp.service.IDocumentoResidenciaService;

@Primary
@Service
public class DocumentoResidenciaServiceJpa implements IDocumentoResidenciaService {

	@Autowired
	private DocumentoResidenciaRepository repoDocumentoResidencia;

	@Override
	public List<DocumentoResidencia> buscarTodosDocRes() {
		return repoDocumentoResidencia.findByEstatus(1);
	}

	@Override
	public List<DocumentoResidencia> buscarDocumentosActivos(String tipoDocumento) {
		if (tipoDocumento != null && !tipoDocumento.isBlank()) {
			return repoDocumentoResidencia.findByTipoDocumentoAndEstatus(tipoDocumento, 1);
		}
		return repoDocumentoResidencia.findByEstatus(1);
	}

	@Override
	public List<DocumentoResidencia> buscarTodosDocumentos(String tipoDocumento) {
		if (tipoDocumento != null && !tipoDocumento.isBlank()) {
			return repoDocumentoResidencia.findByTipoDocumento(tipoDocumento);
		}
		return repoDocumentoResidencia.findAll();
	}

	@Override
	public List<DocumentoResidencia> buscarTodosIncluyendoInactivos() {
		return repoDocumentoResidencia.findAll();
	}

	@Override
	public List<DocumentoResidencia> buscarPorProyectoResidencia(Integer idProyectoResidencia) {
		return repoDocumentoResidencia.findByProyectoResidenciaIdProyectoResidenciaAndEstatus(idProyectoResidencia, 1);
	}

	@Override
	public DocumentoResidencia buscarPorProyectoYTipo(Integer idProyectoResidencia, String tipoDocumento) {
		return repoDocumentoResidencia.findByProyectoResidenciaIdProyectoResidenciaAndTipoDocumentoAndEstatus(
				idProyectoResidencia, tipoDocumento, 1
		);
	}

	@Override
	public void guardarDocumentoResidencia(DocumentoResidencia documentoResidencia) {
		repoDocumentoResidencia.save(documentoResidencia);
	}

	@Override
	public DocumentoResidencia buscarPorIdDocRes(Integer id) {
		Optional<DocumentoResidencia> optional = repoDocumentoResidencia.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminarDocumentoResidencia(Integer id) {
		DocumentoResidencia documentoResidencia = repoDocumentoResidencia.findById(id).orElse(null);

		if (documentoResidencia != null) {
			documentoResidencia.setEstatus(0);
			repoDocumentoResidencia.save(documentoResidencia);
		}
	}

	@Override
	public void activarDocumentoResidencia(Integer id) {
		DocumentoResidencia documentoResidencia = repoDocumentoResidencia.findById(id).orElse(null);

		if (documentoResidencia != null) {
			documentoResidencia.setEstatus(1);
			repoDocumentoResidencia.save(documentoResidencia);
		}
	}
}
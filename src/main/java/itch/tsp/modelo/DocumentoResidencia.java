package itch.tsp.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "documento_residencia")
public class DocumentoResidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_residencia")
	private Integer idDocumentoResidencia;

	@Column(name = "tipo_documento")
	private String tipoDocumento;

	@Column(name = "nombre_archivo")
	private String nombreArchivo;

	@Column(name = "ruta_archivo")
	private String rutaArchivo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_carga")
	private Date fechaCarga;

	@Column(name = "observaciones", columnDefinition = "TEXT")
	private String observaciones;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proyecto_residencia")
	private ProyectoResidencia proyectoResidencia;

	public Integer getIdDocumentoResidencia() {
		return idDocumentoResidencia;
	}

	public void setIdDocumentoResidencia(Integer idDocumentoResidencia) {
		this.idDocumentoResidencia = idDocumentoResidencia;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public ProyectoResidencia getProyectoResidencia() {
		return proyectoResidencia;
	}

	public void setProyectoResidencia(ProyectoResidencia proyectoResidencia) {
		this.proyectoResidencia = proyectoResidencia;
	}

	@Override
	public String toString() {
		return "DocumentoResidencia [idDocumentoResidencia=" + idDocumentoResidencia + ", tipoDocumento="
				+ tipoDocumento + ", nombreArchivo=" + nombreArchivo + ", fechaCarga=" + fechaCarga + ", estatus="
				+ estatus + "]";
	}
}
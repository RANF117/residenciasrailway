package itch.tsp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "propuesta_proyecto")
public class PropuestaProyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_propuesta_proyecto")
	private Integer idPropuestaProyecto;

	@Column(name = "nombre_proyecto")
	private String nombreProyecto;

	@Column(name = "objetivo", columnDefinition = "TEXT")
	private String objetivo;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "justificacion", columnDefinition = "TEXT")
	private String justificacion;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "estado_revision")
	private String estadoRevision;

	@Column(name = "observaciones_revision", columnDefinition = "TEXT")
	private String observacionesRevision;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_residente")
	private Residente residente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Integer getIdPropuestaProyecto() {
		return idPropuestaProyecto;
	}

	public void setIdPropuestaProyecto(Integer idPropuestaProyecto) {
		this.idPropuestaProyecto = idPropuestaProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getEstadoRevision() {
		return estadoRevision;
	}

	public void setEstadoRevision(String estadoRevision) {
		this.estadoRevision = estadoRevision;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Residente getResidente() {
		return residente;
	}

	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "PropuestaProyecto [idPropuestaProyecto=" + idPropuestaProyecto + ", nombreProyecto=" + nombreProyecto
				+ ", objetivo=" + objetivo + ", descripcion=" + descripcion + ", justificacion=" + justificacion
				+ ", periodo=" + periodo + ", estadoRevision=" + estadoRevision + ", observacionesRevision="
				+ observacionesRevision + ", estatus=" + estatus + "]";
	}
}
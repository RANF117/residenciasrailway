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
@Table(name = "reporte_preliminar")
public class ReportePreliminar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reporte_preliminar")
	private Integer idReportePreliminar;

	@Column(name = "objetivo_proyecto", columnDefinition = "TEXT")
	private String objetivoProyecto;

	@Column(name = "actividades_realizadas", columnDefinition = "TEXT")
	private String actividadesRealizadas;

	@Column(name = "avance_general", columnDefinition = "TEXT")
	private String avanceGeneral;

	@Column(name = "observaciones", columnDefinition = "TEXT")
	private String observaciones;

	@Column(name = "dictamen")
	private String dictamen;

	@Column(name = "observaciones_dictamen", columnDefinition = "TEXT")
	private String observacionesDictamen;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proyecto_residencia")
	private ProyectoResidencia proyectoResidencia;

	public Integer getIdReportePreliminar() {
		return idReportePreliminar;
	}

	public void setIdReportePreliminar(Integer idReportePreliminar) {
		this.idReportePreliminar = idReportePreliminar;
	}

	public String getObjetivoProyecto() {
		return objetivoProyecto;
	}

	public void setObjetivoProyecto(String objetivoProyecto) {
		this.objetivoProyecto = objetivoProyecto;
	}

	public String getActividadesRealizadas() {
		return actividadesRealizadas;
	}

	public void setActividadesRealizadas(String actividadesRealizadas) {
		this.actividadesRealizadas = actividadesRealizadas;
	}

	public String getAvanceGeneral() {
		return avanceGeneral;
	}

	public void setAvanceGeneral(String avanceGeneral) {
		this.avanceGeneral = avanceGeneral;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDictamen() {
		return dictamen;
	}

	public void setDictamen(String dictamen) {
		this.dictamen = dictamen;
	}

	public String getObservacionesDictamen() {
		return observacionesDictamen;
	}

	public void setObservacionesDictamen(String observacionesDictamen) {
		this.observacionesDictamen = observacionesDictamen;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
		return "ReportePreliminar [idReportePreliminar=" + idReportePreliminar + ", dictamen=" + dictamen
				+ ", fechaEntrega=" + fechaEntrega + ", estatus=" + estatus + "]";
	}
}
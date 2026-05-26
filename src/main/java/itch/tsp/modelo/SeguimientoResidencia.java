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
@Table(name = "seguimiento_residencia")
public class SeguimientoResidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_seguimiento_residencia")
	private Integer idSeguimientoResidencia;

	@Column(name = "tipo_seguimiento")
	private String tipoSeguimiento;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_evaluacion")
	private Date fechaEvaluacion;

	@Column(name = "calificacion_asesor_interno")
	private Double calificacionAsesorInterno;

	@Column(name = "calificacion_asesor_externo")
	private Double calificacionAsesorExterno;

	@Column(name = "calificacion_final")
	private Double calificacionFinal;

	@Column(name = "observaciones", columnDefinition = "TEXT")
	private String observaciones;

	@Column(name = "observaciones_interno", columnDefinition = "TEXT")
	private String observacionesInterno;

	@Column(name = "observaciones_externo", columnDefinition = "TEXT")
	private String observacionesExterno;

	@Column(name = "interno_asistencia")
	private Double internoAsistencia;

	@Column(name = "interno_conocimiento")
	private Double internoConocimiento;

	@Column(name = "interno_trabajo_equipo")
	private Double internoTrabajoEquipo;

	@Column(name = "interno_proactividad")
	private Double internoProactividad;

	@Column(name = "interno_orden")
	private Double internoOrden;

	@Column(name = "interno_mejoras")
	private Double internoMejoras;

	@Column(name = "externo_asistencia")
	private Double externoAsistencia;

	@Column(name = "externo_trabajo_equipo")
	private Double externoTrabajoEquipo;

	@Column(name = "externo_iniciativa")
	private Double externoIniciativa;

	@Column(name = "externo_mejoras")
	private Double externoMejoras;

	@Column(name = "externo_objetivos")
	private Double externoObjetivos;

	@Column(name = "externo_orden")
	private Double externoOrden;

	@Column(name = "externo_liderazgo")
	private Double externoLiderazgo;

	@Column(name = "externo_conocimiento")
	private Double externoConocimiento;

	@Column(name = "externo_etica")
	private Double externoEtica;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proyecto_residencia")
	private ProyectoResidencia proyectoResidencia;

	public Integer getIdSeguimientoResidencia() {
		return idSeguimientoResidencia;
	}

	public void setIdSeguimientoResidencia(Integer idSeguimientoResidencia) {
		this.idSeguimientoResidencia = idSeguimientoResidencia;
	}

	public String getTipoSeguimiento() {
		return tipoSeguimiento;
	}

	public void setTipoSeguimiento(String tipoSeguimiento) {
		this.tipoSeguimiento = tipoSeguimiento;
	}

	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}

	public Double getCalificacionAsesorInterno() {
		return calificacionAsesorInterno;
	}

	public void setCalificacionAsesorInterno(Double calificacionAsesorInterno) {
		this.calificacionAsesorInterno = calificacionAsesorInterno;
	}

	public Double getCalificacionAsesorExterno() {
		return calificacionAsesorExterno;
	}

	public void setCalificacionAsesorExterno(Double calificacionAsesorExterno) {
		this.calificacionAsesorExterno = calificacionAsesorExterno;
	}

	public Double getCalificacionFinal() {
		return calificacionFinal;
	}

	public void setCalificacionFinal(Double calificacionFinal) {
		this.calificacionFinal = calificacionFinal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservacionesInterno() {
		return observacionesInterno;
	}

	public void setObservacionesInterno(String observacionesInterno) {
		this.observacionesInterno = observacionesInterno;
	}

	public String getObservacionesExterno() {
		return observacionesExterno;
	}

	public void setObservacionesExterno(String observacionesExterno) {
		this.observacionesExterno = observacionesExterno;
	}

	public Double getInternoAsistencia() {
		return internoAsistencia;
	}

	public void setInternoAsistencia(Double internoAsistencia) {
		this.internoAsistencia = internoAsistencia;
	}

	public Double getInternoConocimiento() {
		return internoConocimiento;
	}

	public void setInternoConocimiento(Double internoConocimiento) {
		this.internoConocimiento = internoConocimiento;
	}

	public Double getInternoTrabajoEquipo() {
		return internoTrabajoEquipo;
	}

	public void setInternoTrabajoEquipo(Double internoTrabajoEquipo) {
		this.internoTrabajoEquipo = internoTrabajoEquipo;
	}

	public Double getInternoProactividad() {
		return internoProactividad;
	}

	public void setInternoProactividad(Double internoProactividad) {
		this.internoProactividad = internoProactividad;
	}

	public Double getInternoOrden() {
		return internoOrden;
	}

	public void setInternoOrden(Double internoOrden) {
		this.internoOrden = internoOrden;
	}

	public Double getInternoMejoras() {
		return internoMejoras;
	}

	public void setInternoMejoras(Double internoMejoras) {
		this.internoMejoras = internoMejoras;
	}

	public Double getExternoAsistencia() {
		return externoAsistencia;
	}

	public void setExternoAsistencia(Double externoAsistencia) {
		this.externoAsistencia = externoAsistencia;
	}

	public Double getExternoTrabajoEquipo() {
		return externoTrabajoEquipo;
	}

	public void setExternoTrabajoEquipo(Double externoTrabajoEquipo) {
		this.externoTrabajoEquipo = externoTrabajoEquipo;
	}

	public Double getExternoIniciativa() {
		return externoIniciativa;
	}

	public void setExternoIniciativa(Double externoIniciativa) {
		this.externoIniciativa = externoIniciativa;
	}

	public Double getExternoMejoras() {
		return externoMejoras;
	}

	public void setExternoMejoras(Double externoMejoras) {
		this.externoMejoras = externoMejoras;
	}

	public Double getExternoObjetivos() {
		return externoObjetivos;
	}

	public void setExternoObjetivos(Double externoObjetivos) {
		this.externoObjetivos = externoObjetivos;
	}

	public Double getExternoOrden() {
		return externoOrden;
	}

	public void setExternoOrden(Double externoOrden) {
		this.externoOrden = externoOrden;
	}

	public Double getExternoLiderazgo() {
		return externoLiderazgo;
	}

	public void setExternoLiderazgo(Double externoLiderazgo) {
		this.externoLiderazgo = externoLiderazgo;
	}

	public Double getExternoConocimiento() {
		return externoConocimiento;
	}

	public void setExternoConocimiento(Double externoConocimiento) {
		this.externoConocimiento = externoConocimiento;
	}

	public Double getExternoEtica() {
		return externoEtica;
	}

	public void setExternoEtica(Double externoEtica) {
		this.externoEtica = externoEtica;
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
		return "SeguimientoResidencia [idSeguimientoResidencia=" + idSeguimientoResidencia
				+ ", tipoSeguimiento=" + tipoSeguimiento
				+ ", fechaEvaluacion=" + fechaEvaluacion
				+ ", calificacionFinal=" + calificacionFinal
				+ ", estatus=" + estatus + "]";
	}
}
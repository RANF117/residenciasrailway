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
@Table(name = "proyecto_residencia")
public class ProyectoResidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proyecto_residencia")
	private Integer idProyectoResidencia;

	@Column(name = "origen_proyecto")
	private String origenProyecto; 

	@Column(name = "nombre_proyecto")
	private String nombreProyecto;

	@Column(name = "objetivo", columnDefinition = "TEXT")
	private String objetivo;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "periodo")
	private String periodo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Column(name = "horas")
	private Integer horas;

	@Column(name = "etapa_actual")
	private String etapaActual;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_residente")
	private Residente residente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_asesor_interno")
	private AsesorInterno asesorInterno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_asesor_externo")
	private AsesorExterno asesorExterno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proyecto_banco")
	private ProyectoBanco proyectoBanco;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_propuesta_proyecto")
	private PropuestaProyecto propuestaProyecto;
	
	@Column(name = "calificacion_final_residencia")
	private Double calificacionFinalResidencia;

	public Integer getIdProyectoResidencia() {
		return idProyectoResidencia;
	}

	public void setIdProyectoResidencia(Integer idProyectoResidencia) {
		this.idProyectoResidencia = idProyectoResidencia;
	}

	public String getOrigenProyecto() {
		return origenProyecto;
	}

	public void setOrigenProyecto(String origenProyecto) {
		this.origenProyecto = origenProyecto;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String getEtapaActual() {
		return etapaActual;
	}

	public void setEtapaActual(String etapaActual) {
		this.etapaActual = etapaActual;
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

	public AsesorInterno getAsesorInterno() {
		return asesorInterno;
	}

	public void setAsesorInterno(AsesorInterno asesorInterno) {
		this.asesorInterno = asesorInterno;
	}

	public AsesorExterno getAsesorExterno() {
		return asesorExterno;
	}

	public void setAsesorExterno(AsesorExterno asesorExterno) {
		this.asesorExterno = asesorExterno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ProyectoBanco getProyectoBanco() {
		return proyectoBanco;
	}

	public void setProyectoBanco(ProyectoBanco proyectoBanco) {
		this.proyectoBanco = proyectoBanco;
	}

	public PropuestaProyecto getPropuestaProyecto() {
		return propuestaProyecto;
	}

	public void setPropuestaProyecto(PropuestaProyecto propuestaProyecto) {
		this.propuestaProyecto = propuestaProyecto;
	}
	public Double getCalificacionFinalResidencia() {
		return calificacionFinalResidencia;
	}

	public void setCalificacionFinalResidencia(Double calificacionFinalResidencia) {
		this.calificacionFinalResidencia = calificacionFinalResidencia;
	}

	@Override
	public String toString() {
		return "ProyectoResidencia [idProyectoResidencia=" + idProyectoResidencia + ", origenProyecto=" + origenProyecto
				+ ", nombreProyecto=" + nombreProyecto + ", objetivo=" + objetivo + ", descripcion=" + descripcion
				+ ", periodo=" + periodo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", horas="
				+ horas + ", etapaActual=" + etapaActual + ", estatus=" + estatus + ", residente=" + residente
				+ ", asesorInterno=" + asesorInterno + ", asesorExterno=" + asesorExterno + ", empresa=" + empresa
				+ ", proyectoBanco=" + proyectoBanco + ", propuestaProyecto=" + propuestaProyecto
				+ ", calificacionFinalResidencia=" + calificacionFinalResidencia + "]";
	}

	
}
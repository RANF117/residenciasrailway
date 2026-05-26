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
@Table(name = "evaluacion_reporte_final")
public class EvaluacionReporteFinal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evaluacion_reporte_final")
	private Integer idEvaluacionReporteFinal;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_evaluacion")
	private Date fechaEvaluacion;

	@Column(name = "calificacion_asesor_interno")
	private Double calificacionAsesorInterno;

	@Column(name = "calificacion_asesor_externo")
	private Double calificacionAsesorExterno;

	@Column(name = "calificacion_final_reporte")
	private Double calificacionFinalReporte;

	@Column(name = "observaciones_interno", columnDefinition = "TEXT")
	private String observacionesInterno;

	@Column(name = "observaciones_externo", columnDefinition = "TEXT")
	private String observacionesExterno;

	@Column(name = "interno_portada")
	private Double internoPortada;
	@Column(name = "interno_agradecimientos")
	private Double internoAgradecimientos;
	@Column(name = "interno_resumen")
	private Double internoResumen;
	@Column(name = "interno_indice")
	private Double internoIndice;
	@Column(name = "interno_introduccion")
	private Double internoIntroduccion;
	@Column(name = "interno_problemas")
	private Double internoProblemas;
	@Column(name = "interno_objetivos")
	private Double internoObjetivos;
	@Column(name = "interno_marco_teorico")
	private Double internoMarcoTeorico;
	@Column(name = "interno_procedimiento")
	private Double internoProcedimiento;
	@Column(name = "interno_resultados")
	private Double internoResultados;
	@Column(name = "interno_conclusiones")
	private Double internoConclusiones;
	@Column(name = "interno_competencias")
	private Double internoCompetencias;
	@Column(name = "interno_fuentes")
	private Double internoFuentes;

	@Column(name = "externo_portada")
	private Double externoPortada;
	@Column(name = "externo_agradecimientos")
	private Double externoAgradecimientos;
	@Column(name = "externo_resumen")
	private Double externoResumen;
	@Column(name = "externo_indice")
	private Double externoIndice;
	@Column(name = "externo_introduccion")
	private Double externoIntroduccion;
	@Column(name = "externo_problemas")
	private Double externoProblemas;
	@Column(name = "externo_objetivos")
	private Double externoObjetivos;
	@Column(name = "externo_marco_teorico")
	private Double externoMarcoTeorico;
	@Column(name = "externo_procedimiento")
	private Double externoProcedimiento;
	@Column(name = "externo_resultados")
	private Double externoResultados;
	@Column(name = "externo_conclusiones")
	private Double externoConclusiones;
	@Column(name = "externo_competencias")
	private Double externoCompetencias;
	@Column(name = "externo_fuentes")
	private Double externoFuentes;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proyecto_residencia")
	private ProyectoResidencia proyectoResidencia;

	public Integer getIdEvaluacionReporteFinal() {
		return idEvaluacionReporteFinal;
	}

	public void setIdEvaluacionReporteFinal(Integer idEvaluacionReporteFinal) {
		this.idEvaluacionReporteFinal = idEvaluacionReporteFinal;
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

	public Double getCalificacionFinalReporte() {
		return calificacionFinalReporte;
	}

	public void setCalificacionFinalReporte(Double calificacionFinalReporte) {
		this.calificacionFinalReporte = calificacionFinalReporte;
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

	public Double getInternoPortada() { return internoPortada; }
	public void setInternoPortada(Double internoPortada) { this.internoPortada = internoPortada; }

	public Double getInternoAgradecimientos() { return internoAgradecimientos; }
	public void setInternoAgradecimientos(Double internoAgradecimientos) { this.internoAgradecimientos = internoAgradecimientos; }

	public Double getInternoResumen() { return internoResumen; }
	public void setInternoResumen(Double internoResumen) { this.internoResumen = internoResumen; }

	public Double getInternoIndice() { return internoIndice; }
	public void setInternoIndice(Double internoIndice) { this.internoIndice = internoIndice; }

	public Double getInternoIntroduccion() { return internoIntroduccion; }
	public void setInternoIntroduccion(Double internoIntroduccion) { this.internoIntroduccion = internoIntroduccion; }

	public Double getInternoProblemas() { return internoProblemas; }
	public void setInternoProblemas(Double internoProblemas) { this.internoProblemas = internoProblemas; }

	public Double getInternoObjetivos() { return internoObjetivos; }
	public void setInternoObjetivos(Double internoObjetivos) { this.internoObjetivos = internoObjetivos; }

	public Double getInternoMarcoTeorico() { return internoMarcoTeorico; }
	public void setInternoMarcoTeorico(Double internoMarcoTeorico) { this.internoMarcoTeorico = internoMarcoTeorico; }

	public Double getInternoProcedimiento() { return internoProcedimiento; }
	public void setInternoProcedimiento(Double internoProcedimiento) { this.internoProcedimiento = internoProcedimiento; }

	public Double getInternoResultados() { return internoResultados; }
	public void setInternoResultados(Double internoResultados) { this.internoResultados = internoResultados; }

	public Double getInternoConclusiones() { return internoConclusiones; }
	public void setInternoConclusiones(Double internoConclusiones) { this.internoConclusiones = internoConclusiones; }

	public Double getInternoCompetencias() { return internoCompetencias; }
	public void setInternoCompetencias(Double internoCompetencias) { this.internoCompetencias = internoCompetencias; }

	public Double getInternoFuentes() { return internoFuentes; }
	public void setInternoFuentes(Double internoFuentes) { this.internoFuentes = internoFuentes; }

	public Double getExternoPortada() { return externoPortada; }
	public void setExternoPortada(Double externoPortada) { this.externoPortada = externoPortada; }

	public Double getExternoAgradecimientos() { return externoAgradecimientos; }
	public void setExternoAgradecimientos(Double externoAgradecimientos) { this.externoAgradecimientos = externoAgradecimientos; }

	public Double getExternoResumen() { return externoResumen; }
	public void setExternoResumen(Double externoResumen) { this.externoResumen = externoResumen; }

	public Double getExternoIndice() { return externoIndice; }
	public void setExternoIndice(Double externoIndice) { this.externoIndice = externoIndice; }

	public Double getExternoIntroduccion() { return externoIntroduccion; }
	public void setExternoIntroduccion(Double externoIntroduccion) { this.externoIntroduccion = externoIntroduccion; }

	public Double getExternoProblemas() { return externoProblemas; }
	public void setExternoProblemas(Double externoProblemas) { this.externoProblemas = externoProblemas; }

	public Double getExternoObjetivos() { return externoObjetivos; }
	public void setExternoObjetivos(Double externoObjetivos) { this.externoObjetivos = externoObjetivos; }

	public Double getExternoMarcoTeorico() { return externoMarcoTeorico; }
	public void setExternoMarcoTeorico(Double externoMarcoTeorico) { this.externoMarcoTeorico = externoMarcoTeorico; }

	public Double getExternoProcedimiento() { return externoProcedimiento; }
	public void setExternoProcedimiento(Double externoProcedimiento) { this.externoProcedimiento = externoProcedimiento; }

	public Double getExternoResultados() { return externoResultados; }
	public void setExternoResultados(Double externoResultados) { this.externoResultados = externoResultados; }

	public Double getExternoConclusiones() { return externoConclusiones; }
	public void setExternoConclusiones(Double externoConclusiones) { this.externoConclusiones = externoConclusiones; }

	public Double getExternoCompetencias() { return externoCompetencias; }
	public void setExternoCompetencias(Double externoCompetencias) { this.externoCompetencias = externoCompetencias; }

	public Double getExternoFuentes() { return externoFuentes; }
	public void setExternoFuentes(Double externoFuentes) { this.externoFuentes = externoFuentes; }

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
		return "EvaluacionReporteFinal [idEvaluacionReporteFinal=" + idEvaluacionReporteFinal + ", fechaEvaluacion="
				+ fechaEvaluacion + ", calificacionAsesorInterno=" + calificacionAsesorInterno
				+ ", calificacionAsesorExterno=" + calificacionAsesorExterno + ", calificacionFinalReporte="
				+ calificacionFinalReporte + ", observacionesInterno=" + observacionesInterno
				+ ", observacionesExterno=" + observacionesExterno + ", internoPortada=" + internoPortada
				+ ", internoAgradecimientos=" + internoAgradecimientos + ", internoResumen=" + internoResumen
				+ ", internoIndice=" + internoIndice + ", internoIntroduccion=" + internoIntroduccion
				+ ", internoProblemas=" + internoProblemas + ", internoObjetivos=" + internoObjetivos
				+ ", internoMarcoTeorico=" + internoMarcoTeorico + ", internoProcedimiento=" + internoProcedimiento
				+ ", internoResultados=" + internoResultados + ", internoConclusiones=" + internoConclusiones
				+ ", internoCompetencias=" + internoCompetencias + ", internoFuentes=" + internoFuentes
				+ ", externoPortada=" + externoPortada + ", externoAgradecimientos=" + externoAgradecimientos
				+ ", externoResumen=" + externoResumen + ", externoIndice=" + externoIndice + ", externoIntroduccion="
				+ externoIntroduccion + ", externoProblemas=" + externoProblemas + ", externoObjetivos="
				+ externoObjetivos + ", externoMarcoTeorico=" + externoMarcoTeorico + ", externoProcedimiento="
				+ externoProcedimiento + ", externoResultados=" + externoResultados + ", externoConclusiones="
				+ externoConclusiones + ", externoCompetencias=" + externoCompetencias + ", externoFuentes="
				+ externoFuentes + ", estatus=" + estatus + ", proyectoResidencia=" + proyectoResidencia + "]";
	}
	
}
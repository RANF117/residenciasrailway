package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.modelo.EvaluacionReporteFinal;
import itch.tsp.service.IEvaluacionReporteFinalService;
import itch.tsp.service.IProyectoResidenciaService;

@RequestMapping("/evaluacionReporteFinal")
@Controller
public class EvaluacionReporteFinalController {

	@Autowired
	private IEvaluacionReporteFinalService evaluacionReporteFinalService;

	@Autowired
	private IProyectoResidenciaService proyectoResidenciaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/mostrar")
	public String mostrarDatos(
			@RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
			Model model) {

		List<EvaluacionReporteFinal> lista = evaluacionReporteFinalService.buscarEvaluacionesActivas(nombreProyecto);
		model.addAttribute("evaluacionReporteFinal", lista);
		model.addAttribute("nombreProyecto", nombreProyecto);

		return "evaluacionReporteFinal/datosEvaluacionReporteFinal";
	}

	@GetMapping("/estatus")
	public String mostrarEstatus(
			@RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
			Model model) {

		List<EvaluacionReporteFinal> lista = evaluacionReporteFinalService.buscarTodasEvaluaciones(nombreProyecto);
		model.addAttribute("evaluacionReporteFinal", lista);
		model.addAttribute("nombreProyecto", nombreProyecto);

		return "evaluacionReporteFinal/estatusEvaluacionReporteFinal";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(
			@RequestParam(name = "idProyectoResidencia", required = false) Integer idProyectoResidencia,
			Authentication authentication,
			Model model) {

		EvaluacionReporteFinal evaluacion = new EvaluacionReporteFinal();

		if (idProyectoResidencia != null) {
			EvaluacionReporteFinal existente = evaluacionReporteFinalService.buscarPorProyecto(idProyectoResidencia);

			if (existente != null) {
				evaluacion = existente;
			} else {
				evaluacion.setProyectoResidencia(proyectoResidenciaService.buscarPorIdProyRes(idProyectoResidencia));
			}
		}

		boolean mostrarInterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		boolean mostrarExterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		model.addAttribute("evaluacionReporteFinalObj", evaluacion);
		model.addAttribute("proyectosResidencia", proyectoResidenciaService.buscarTodosProyRes());
		model.addAttribute("mostrarInterno", mostrarInterno);
		model.addAttribute("mostrarExterno", mostrarExterno);

		return "evaluacionReporteFinal/formEvaluacionReporteFinal";
	}

	@PostMapping("/guardar")
	public String guardar(EvaluacionReporteFinal evaluacion,
			Authentication authentication,
			RedirectAttributes redirectAttributes) {

		EvaluacionReporteFinal base = evaluacionReporteFinalService.buscarPorProyecto(
				evaluacion.getProyectoResidencia().getIdProyectoResidencia());

		if (base == null) {
			base = new EvaluacionReporteFinal();
			base.setProyectoResidencia(evaluacion.getProyectoResidencia());
			base.setEstatus(1);
		}

		base.setFechaEvaluacion(evaluacion.getFechaEvaluacion());

		boolean mostrarInterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		boolean mostrarExterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		if (mostrarInterno) {
			base.setInternoPortada(evaluacion.getInternoPortada());
			base.setInternoAgradecimientos(evaluacion.getInternoAgradecimientos());
			base.setInternoResumen(evaluacion.getInternoResumen());
			base.setInternoIndice(evaluacion.getInternoIndice());
			base.setInternoIntroduccion(evaluacion.getInternoIntroduccion());
			base.setInternoProblemas(evaluacion.getInternoProblemas());
			base.setInternoObjetivos(evaluacion.getInternoObjetivos());
			base.setInternoMarcoTeorico(evaluacion.getInternoMarcoTeorico());
			base.setInternoProcedimiento(evaluacion.getInternoProcedimiento());
			base.setInternoResultados(evaluacion.getInternoResultados());
			base.setInternoConclusiones(evaluacion.getInternoConclusiones());
			base.setInternoCompetencias(evaluacion.getInternoCompetencias());
			base.setInternoFuentes(evaluacion.getInternoFuentes());
			base.setObservacionesInterno(evaluacion.getObservacionesInterno());
			base.setCalificacionAsesorInterno(calcularInterno(evaluacion));
		}

		if (mostrarExterno) {
			base.setExternoPortada(evaluacion.getExternoPortada());
			base.setExternoAgradecimientos(evaluacion.getExternoAgradecimientos());
			base.setExternoResumen(evaluacion.getExternoResumen());
			base.setExternoIndice(evaluacion.getExternoIndice());
			base.setExternoIntroduccion(evaluacion.getExternoIntroduccion());
			base.setExternoProblemas(evaluacion.getExternoProblemas());
			base.setExternoObjetivos(evaluacion.getExternoObjetivos());
			base.setExternoMarcoTeorico(evaluacion.getExternoMarcoTeorico());
			base.setExternoProcedimiento(evaluacion.getExternoProcedimiento());
			base.setExternoResultados(evaluacion.getExternoResultados());
			base.setExternoConclusiones(evaluacion.getExternoConclusiones());
			base.setExternoCompetencias(evaluacion.getExternoCompetencias());
			base.setExternoFuentes(evaluacion.getExternoFuentes());
			base.setObservacionesExterno(evaluacion.getObservacionesExterno());
			base.setCalificacionAsesorExterno(calcularExterno(evaluacion));
		}

		if (base.getCalificacionAsesorInterno() != null && base.getCalificacionAsesorExterno() != null) {
			base.setCalificacionFinalReporte((base.getCalificacionAsesorInterno() + base.getCalificacionAsesorExterno()) / 2.0);
		} else if (base.getCalificacionAsesorInterno() != null) {
			base.setCalificacionFinalReporte(base.getCalificacionAsesorInterno());
		} else if (base.getCalificacionAsesorExterno() != null) {
			base.setCalificacionFinalReporte(base.getCalificacionAsesorExterno());
		}

		evaluacionReporteFinalService.guardar(base);
		redirectAttributes.addFlashAttribute("msg", "Evaluación del reporte final guardada correctamente");
		return "redirect:/evaluacionReporteFinal/mostrar";
	}

	@GetMapping("/ver/{id}")
	public String verDetalle(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("evaluacionReporteFinalObj", evaluacionReporteFinalService.buscarPorId(id));
		return "evaluacionReporteFinal/detalleEvaluacionReporteFinal";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Authentication authentication, Model model) {
		EvaluacionReporteFinal evaluacion = evaluacionReporteFinalService.buscarPorId(id);

		boolean mostrarInterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		boolean mostrarExterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		model.addAttribute("evaluacionReporteFinalObj", evaluacion);
		model.addAttribute("proyectosResidencia", proyectoResidenciaService.buscarTodosProyRes());
		model.addAttribute("mostrarInterno", mostrarInterno);
		model.addAttribute("mostrarExterno", mostrarExterno);

		return "evaluacionReporteFinal/formEvaluacionReporteFinal";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		evaluacionReporteFinalService.eliminar(id);
		return "redirect:/evaluacionReporteFinal/mostrar";
	}

	@GetMapping("/activar/{id}")
	public String activar(@PathVariable("id") Integer id) {
		evaluacionReporteFinalService.activar(id);
		return "redirect:/evaluacionReporteFinal/estatus";
	}

	private Double calcularInterno(EvaluacionReporteFinal e) {
		return n(e.getInternoPortada())
				+ n(e.getInternoAgradecimientos())
				+ n(e.getInternoResumen())
				+ n(e.getInternoIndice())
				+ n(e.getInternoIntroduccion())
				+ n(e.getInternoProblemas())
				+ n(e.getInternoObjetivos())
				+ n(e.getInternoMarcoTeorico())
				+ n(e.getInternoProcedimiento())
				+ n(e.getInternoResultados())
				+ n(e.getInternoConclusiones())
				+ n(e.getInternoCompetencias())
				+ n(e.getInternoFuentes());
	}

	private Double calcularExterno(EvaluacionReporteFinal e) {
		return n(e.getExternoPortada())
				+ n(e.getExternoAgradecimientos())
				+ n(e.getExternoResumen())
				+ n(e.getExternoIndice())
				+ n(e.getExternoIntroduccion())
				+ n(e.getExternoProblemas())
				+ n(e.getExternoObjetivos())
				+ n(e.getExternoMarcoTeorico())
				+ n(e.getExternoProcedimiento())
				+ n(e.getExternoResultados())
				+ n(e.getExternoConclusiones())
				+ n(e.getExternoCompetencias())
				+ n(e.getExternoFuentes());
	}

	private Double n(Double valor) {
		return valor == null ? 0.0 : valor;
	}
}
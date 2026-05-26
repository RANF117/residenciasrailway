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

import itch.tsp.modelo.SeguimientoResidencia;
import itch.tsp.service.IProyectoResidenciaService;
import itch.tsp.service.ISeguimientoResidenciaService;

@RequestMapping("/seguimientoResidencia")
@Controller
public class SeguimientoResidenciaController {

	@Autowired
	private ISeguimientoResidenciaService seguimientoResidenciaService;

	@Autowired
	private IProyectoResidenciaService proyectoResidenciaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/mostrarSeguimientos")
	public String mostrarDatos(
			@RequestParam(name = "tipoSeguimiento", required = false) String tipoSeguimiento,
			Model model) {

		List<SeguimientoResidencia> lista = seguimientoResidenciaService.buscarSeguimientosActivos(tipoSeguimiento);
		model.addAttribute("seguimientoResidencia", lista);
		model.addAttribute("tipoSeguimiento", tipoSeguimiento);

		return "seguimientoResidencia/datosSeguimientoResidencia";
	}

	@GetMapping("/estatusSeguimientos")
	public String mostrarEstatusSeguimientos(
			@RequestParam(name = "tipoSeguimiento", required = false) String tipoSeguimiento,
			Model model) {

		List<SeguimientoResidencia> lista = seguimientoResidenciaService.buscarTodosSeguimientos(tipoSeguimiento);
		model.addAttribute("seguimientoResidencia", lista);
		model.addAttribute("tipoSeguimiento", tipoSeguimiento);

		return "seguimientoResidencia/estatusSeguimientoResidencia";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(
	        @RequestParam(name = "idProyectoResidencia", required = false) Integer idProyectoResidencia,
	        @RequestParam(name = "tipoSeguimiento", required = false) String tipoSeguimiento,
	        Authentication authentication,
	        Model model) {

	    SeguimientoResidencia seguimientoResidencia = new SeguimientoResidencia();

	    if (idProyectoResidencia != null && tipoSeguimiento != null && !tipoSeguimiento.isBlank()) {
	        SeguimientoResidencia existente = seguimientoResidenciaService.buscarPorProyectoYTipo(idProyectoResidencia, tipoSeguimiento);

	        if (existente != null) {
	            seguimientoResidencia = existente;
	        } else {
	            seguimientoResidencia.setProyectoResidencia(
	                    proyectoResidenciaService.buscarPorIdProyRes(idProyectoResidencia)
	            );
	            seguimientoResidencia.setTipoSeguimiento(tipoSeguimiento);
	        }
	    } else {
	        if (idProyectoResidencia != null) {
	            seguimientoResidencia.setProyectoResidencia(
	                    proyectoResidenciaService.buscarPorIdProyRes(idProyectoResidencia)
	            );
	        }

	        if (tipoSeguimiento != null && !tipoSeguimiento.isBlank()) {
	            seguimientoResidencia.setTipoSeguimiento(tipoSeguimiento);
	        }
	    }

	    model.addAttribute("seguimientoResidencia", seguimientoResidencia);
	    model.addAttribute("proyectosResidencia", proyectoResidenciaService.buscarTodosProyRes());

	    boolean esInterno = authentication != null && authentication.getAuthorities().stream()
	            .anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));
	    boolean esExterno = authentication != null && authentication.getAuthorities().stream()
	            .anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

	    model.addAttribute("mostrarInterno", esInterno);
	    model.addAttribute("mostrarExterno", esExterno);

	    return "seguimientoResidencia/formSeguimientoResidencia";
	}

	@PostMapping("/guardar")
	public String guardarSeguimientoResidencia(SeguimientoResidencia seguimientoResidencia,
			Authentication authentication,
			RedirectAttributes redirectAttributes) {

		SeguimientoResidencia base = seguimientoResidenciaService.buscarPorProyectoYTipo(
				seguimientoResidencia.getProyectoResidencia().getIdProyectoResidencia(),
				seguimientoResidencia.getTipoSeguimiento()
		);

		if (base == null) {
			base = new SeguimientoResidencia();
			base.setProyectoResidencia(seguimientoResidencia.getProyectoResidencia());
			base.setTipoSeguimiento(seguimientoResidencia.getTipoSeguimiento());
			base.setEstatus(1);
		}

		base.setFechaEvaluacion(seguimientoResidencia.getFechaEvaluacion());

		boolean esInterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));
		boolean esExterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		if (esInterno) {
			base.setInternoAsistencia(seguimientoResidencia.getInternoAsistencia());
			base.setInternoConocimiento(seguimientoResidencia.getInternoConocimiento());
			base.setInternoTrabajoEquipo(seguimientoResidencia.getInternoTrabajoEquipo());
			base.setInternoProactividad(seguimientoResidencia.getInternoProactividad());
			base.setInternoOrden(seguimientoResidencia.getInternoOrden());
			base.setInternoMejoras(seguimientoResidencia.getInternoMejoras());
			base.setObservacionesInterno(seguimientoResidencia.getObservacionesInterno());
			base.setCalificacionAsesorInterno(calcularTotalInterno(seguimientoResidencia));
		}

		if (esExterno) {
			base.setExternoAsistencia(seguimientoResidencia.getExternoAsistencia());
			base.setExternoTrabajoEquipo(seguimientoResidencia.getExternoTrabajoEquipo());
			base.setExternoIniciativa(seguimientoResidencia.getExternoIniciativa());
			base.setExternoMejoras(seguimientoResidencia.getExternoMejoras());
			base.setExternoObjetivos(seguimientoResidencia.getExternoObjetivos());
			base.setExternoOrden(seguimientoResidencia.getExternoOrden());
			base.setExternoLiderazgo(seguimientoResidencia.getExternoLiderazgo());
			base.setExternoConocimiento(seguimientoResidencia.getExternoConocimiento());
			base.setExternoEtica(seguimientoResidencia.getExternoEtica());
			base.setObservacionesExterno(seguimientoResidencia.getObservacionesExterno());
			base.setCalificacionAsesorExterno(calcularTotalExterno(seguimientoResidencia));
		}

		base.setObservaciones(construirResumenObservaciones(base));

		if (base.getCalificacionAsesorInterno() != null && base.getCalificacionAsesorExterno() != null) {
			base.setCalificacionFinal((base.getCalificacionAsesorInterno() + base.getCalificacionAsesorExterno()) / 2.0);
		} else if (base.getCalificacionAsesorInterno() != null) {
			base.setCalificacionFinal(base.getCalificacionAsesorInterno());
		} else if (base.getCalificacionAsesorExterno() != null) {
			base.setCalificacionFinal(base.getCalificacionAsesorExterno());
		}

		if (base.getEstatus() == null) {
			base.setEstatus(1);
		}

		seguimientoResidenciaService.guardarSeguimientoResidencia(base);
		redirectAttributes.addFlashAttribute("msg", "Seguimiento guardado correctamente");
		return "redirect:/seguimientoResidencia/mostrarSeguimientos";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleSeguimiento(@PathVariable("id") Integer id, Model model) {
		SeguimientoResidencia seguimientoResidencia = seguimientoResidenciaService.buscarPorIdSeg(id);
		model.addAttribute("seguimientoResidencia", seguimientoResidencia);
		return "seguimientoResidencia/detalleSeguimientoResidencia";
	}

	@GetMapping("/editar/{id}")
	public String editarSeguimiento(@PathVariable("id") Integer id, Authentication authentication, Model model) {
		SeguimientoResidencia seguimientoResidencia = seguimientoResidenciaService.buscarPorIdSeg(id);
		model.addAttribute("seguimientoResidencia", seguimientoResidencia);
		model.addAttribute("proyectosResidencia", proyectoResidenciaService.buscarTodosProyRes());

		boolean esInterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_INTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));
		boolean esExterno = authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ASESOR_EXTERNO") || a.getAuthority().equals("ROLE_JEFE_DIVISION"));

		model.addAttribute("mostrarInterno", esInterno);
		model.addAttribute("mostrarExterno", esExterno);

		return "seguimientoResidencia/formSeguimientoResidencia";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarSeguimiento(@PathVariable("id") Integer id) {
		seguimientoResidenciaService.eliminarSeguimientoResidencia(id);
		return "redirect:/seguimientoResidencia/mostrarSeguimientos";
	}

	@GetMapping("/activar/{id}")
	public String activarSeguimiento(@PathVariable("id") Integer id) {
		seguimientoResidenciaService.activarSeguimientoResidencia(id);
		return "redirect:/seguimientoResidencia/estatusSeguimientos";
	}

	private Double calcularTotalInterno(SeguimientoResidencia s) {
		return n(s.getInternoAsistencia())
				+ n(s.getInternoConocimiento())
				+ n(s.getInternoTrabajoEquipo())
				+ n(s.getInternoProactividad())
				+ n(s.getInternoOrden())
				+ n(s.getInternoMejoras());
	}

	private Double calcularTotalExterno(SeguimientoResidencia s) {
		return n(s.getExternoAsistencia())
				+ n(s.getExternoTrabajoEquipo())
				+ n(s.getExternoIniciativa())
				+ n(s.getExternoMejoras())
				+ n(s.getExternoObjetivos())
				+ n(s.getExternoOrden())
				+ n(s.getExternoLiderazgo())
				+ n(s.getExternoConocimiento())
				+ n(s.getExternoEtica());
	}

	private Double n(Double valor) {
		return valor == null ? 0.0 : valor;
	}

	private String construirResumenObservaciones(SeguimientoResidencia s) {
		String interno = (s.getObservacionesInterno() != null && !s.getObservacionesInterno().isBlank())
				? "Interno: " + s.getObservacionesInterno()
				: "";
		String externo = (s.getObservacionesExterno() != null && !s.getObservacionesExterno().isBlank())
				? "Externo: " + s.getObservacionesExterno()
				: "";

		if (!interno.isBlank() && !externo.isBlank()) {
			return interno + " | " + externo;
		}
		return !interno.isBlank() ? interno : externo;
	}
}
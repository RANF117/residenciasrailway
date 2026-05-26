package itch.tsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.modelo.PropuestaProyecto;
import itch.tsp.modelo.Residente;
import itch.tsp.modelo.Usuario;
import itch.tsp.repository.ResidenteRepository;
import itch.tsp.repository.UsuarioRepository;
import itch.tsp.service.IEmpresaService;
import itch.tsp.service.IPropuestaProyectoService;

@RequestMapping("/propuestaProyecto")
@Controller
public class PropuestaProyectoController {

	@Autowired
	private IPropuestaProyectoService propuestaProyectoService;

	@Autowired
	private IEmpresaService empresaService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ResidenteRepository residenteRepository;

	@GetMapping("/mostrarPropuestas")
	public String mostrarPropuestas(Authentication authentication, Model model) {
		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsernameAndEstatus(username, 1);

		if (usuario == null) {
			model.addAttribute("propuestaProyecto", List.of());
			return "propuestaProyecto/datosPropuestaProyecto";
		}

		Residente residente = residenteRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);

		if (residente == null) {
			model.addAttribute("propuestaProyecto", List.of());
			return "propuestaProyecto/datosPropuestaProyecto";
		}

		List<PropuestaProyecto> lista = propuestaProyectoService.buscarPorResidente(residente.getIdResidente());
		model.addAttribute("propuestaProyecto", lista);

		return "propuestaProyecto/datosPropuestaProyecto";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Authentication authentication, Model model) {
		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsernameAndEstatus(username, 1);

		if (usuario == null) {
			return "redirect:/inicio";
		}

		Residente residente = residenteRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);

		if (residente == null) {
			return "redirect:/inicio";
		}

		PropuestaProyecto propuestaProyecto = new PropuestaProyecto();
		propuestaProyecto.setResidente(residente);

		model.addAttribute("propuestaProyecto", propuestaProyecto);
		model.addAttribute("empresas", empresaService.buscarTodasEmp());
		model.addAttribute("nombreResidente",
				residente.getNombre() + " " + residente.getApellidoPaterno() + " " + residente.getApellidoMaterno());

		return "propuestaProyecto/formPropuestaProyecto";
	}

	@PostMapping("/guardar")
	public String guardarPropuesta(PropuestaProyecto propuestaProyecto,
			Authentication authentication,
			RedirectAttributes redirectAttributes,
			Model model) {

		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsernameAndEstatus(username, 1);

		if (usuario == null) {
			redirectAttributes.addFlashAttribute("msg", "No se encontró el usuario autenticado");
			return "redirect:/propuestaProyecto/mostrarPropuestas";
		}

		Residente residente = residenteRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);

		if (residente == null) {
			redirectAttributes.addFlashAttribute("msg", "No se encontró un residente vinculado a este usuario");
			return "redirect:/propuestaProyecto/mostrarPropuestas";
		}

		propuestaProyecto.setResidente(residente);

		if (propuestaProyecto.getEstatus() == null) {
			propuestaProyecto.setEstatus(1);
		}

		if (propuestaProyecto.getEstadoRevision() == null || propuestaProyecto.getEstadoRevision().isBlank()) {
			propuestaProyecto.setEstadoRevision("PENDIENTE");
		}

		propuestaProyectoService.guardarPropuestaProyecto(propuestaProyecto);
		redirectAttributes.addFlashAttribute("msg", "Propuesta guardada correctamente");
		return "redirect:/propuestaProyecto/mostrarPropuestas";
	}

	@GetMapping("/ver/{id}")
	public String verDetalle(@PathVariable("id") Integer id, Model model) {
		PropuestaProyecto propuestaProyecto = propuestaProyectoService.buscarPorIdPropuesta(id);
		model.addAttribute("propuestaProyecto", propuestaProyecto);
		return "propuestaProyecto/detallePropuestaProyecto";
	}

	@GetMapping("/editar/{id}")
	public String editarPropuesta(@PathVariable("id") Integer id,
			Authentication authentication,
			Model model) {

		PropuestaProyecto propuestaProyecto = propuestaProyectoService.buscarPorIdPropuesta(id);

		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsernameAndEstatus(username, 1);

		if (usuario == null) {
			return "redirect:/propuestaProyecto/mostrarPropuestas";
		}

		Residente residente = residenteRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);

		if (residente == null || propuestaProyecto == null) {
			return "redirect:/propuestaProyecto/mostrarPropuestas";
		}

		propuestaProyecto.setResidente(residente);

		model.addAttribute("propuestaProyecto", propuestaProyecto);
		model.addAttribute("empresas", empresaService.buscarTodasEmp());
		model.addAttribute("nombreResidente",
				residente.getNombre() + " " + residente.getApellidoPaterno() + " " + residente.getApellidoMaterno());

		return "propuestaProyecto/formPropuestaProyecto";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarPropuesta(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		propuestaProyectoService.eliminarPropuestaProyecto(id);
		redirectAttributes.addFlashAttribute("msg", "Propuesta desactivada correctamente");
		return "redirect:/propuestaProyecto/mostrarPropuestas";
	}

	@GetMapping("/revisar")
	public String revisarPropuestas(
			@RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
			Model model) {

		List<PropuestaProyecto> lista = propuestaProyectoService.buscarTodasPropuestas(nombreProyecto);
		model.addAttribute("propuestaProyecto", lista);
		model.addAttribute("nombreProyecto", nombreProyecto);

		return "propuestaProyecto/revisarPropuestaProyecto";
	}
}
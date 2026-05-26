package itch.tsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.modelo.Rol;
import itch.tsp.service.IRolService;

@RequestMapping("/rol")
@Controller
public class rolController {

	@Autowired
	private IRolService rolService;

	@GetMapping("/mostrarRoles")
	public String mostrarDatos(Model model) {
		List<Rol> lista = rolService.buscarTodosRol();
		model.addAttribute("rol", lista);
		return "rol/datosRol";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("rol", new Rol());
		return "rol/formRol";
	}

	@PostMapping("/guardar")
	public String guardarRol(Rol rol, RedirectAttributes redirectAttributes) {

		if (rol.getEstatus() == null) {
			rol.setEstatus(1);
		}

		rolService.guardarRol(rol);
		redirectAttributes.addFlashAttribute("msg", "Rol guardado correctamente");
		return "redirect:/rol/mostrarRoles";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleRol(@PathVariable("id") Integer idRol, Model model) {
		Rol rol = rolService.buscarPorIdRol(idRol);
		model.addAttribute("rol", rol);
		return "rol/detalleRol";
	}

	@GetMapping("/editar/{id}")
	public String editarRol(@PathVariable("id") Integer idRol, Model model) {
		Rol rol = rolService.buscarPorIdRol(idRol);
		model.addAttribute("rol", rol);
		return "rol/formRol";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarRol(@PathVariable("id") Integer id) {
		rolService.eliminarRol(id);
		return "redirect:/rol/mostrarRoles";
	}
}
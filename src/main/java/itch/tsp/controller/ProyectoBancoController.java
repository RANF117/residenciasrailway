package itch.tsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.modelo.ProyectoBanco;
import itch.tsp.service.ICarreraService;
import itch.tsp.service.IProyectoBancoService;

@RequestMapping("/proyectoBanco")
@Controller
public class ProyectoBancoController {

	@Autowired
	private IProyectoBancoService proyectoBancoService;

	@Autowired
	private ICarreraService carreraService;

	@GetMapping("/mostrarProyectosBanco")
	public String mostrarDatos(
			@RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
			@RequestParam(name = "periodo", required = false) String periodo,
			Model model) {

		List<ProyectoBanco> lista = proyectoBancoService.buscarProyectosBancoActivos(nombreProyecto, periodo);
		model.addAttribute("proyectoBanco", lista);
		model.addAttribute("nombreProyecto", nombreProyecto);
		model.addAttribute("periodo", periodo);

		return "proyectoBanco/datosProyectoBanco";
	}

	@GetMapping("/estatusProyectosBanco")
	public String mostrarEstatusProyectosBanco(
			@RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
			@RequestParam(name = "periodo", required = false) String periodo,
			Model model) {

		List<ProyectoBanco> lista = proyectoBancoService.buscarTodosProyectosBanco(nombreProyecto, periodo);
		model.addAttribute("proyectoBanco", lista);
		model.addAttribute("nombreProyecto", nombreProyecto);
		model.addAttribute("periodo", periodo);

		return "proyectoBanco/estatusProyectoBanco";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("proyectoBanco", new ProyectoBanco());
		model.addAttribute("carreras", carreraService.buscarTodasCar());
		return "proyectoBanco/formProyectoBanco";
	}

	@PostMapping("/guardar")
	public String guardarProyectoBanco(ProyectoBanco proyectoBanco, RedirectAttributes redirectAttributes) {

		if (proyectoBanco.getEstatus() == null) {
			proyectoBanco.setEstatus(1);
		}

		proyectoBancoService.guardarProyectoBanco(proyectoBanco);
		redirectAttributes.addFlashAttribute("msg", "Proyecto de banco guardado correctamente");
		return "redirect:/proyectoBanco/mostrarProyectosBanco";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleProyectoBanco(@PathVariable("id") Integer id, Model model) {
		ProyectoBanco proyectoBanco = proyectoBancoService.buscarPorIdProyBan(id);
		model.addAttribute("proyectoBanco", proyectoBanco);
		return "proyectoBanco/detalleProyectoBanco";
	}

	@GetMapping("/editar/{id}")
	public String editarProyectoBanco(@PathVariable("id") Integer id, Model model) {
		ProyectoBanco proyectoBanco = proyectoBancoService.buscarPorIdProyBan(id);
		model.addAttribute("proyectoBanco", proyectoBanco);
		model.addAttribute("carreras", carreraService.buscarTodasCar());
		return "proyectoBanco/formProyectoBanco";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarProyectoBanco(@PathVariable("id") Integer id) {
		proyectoBancoService.eliminarProyectoBanco(id);
		return "redirect:/proyectoBanco/mostrarProyectosBanco";
	}

	@GetMapping("/activar/{id}")
	public String activarProyectoBanco(@PathVariable("id") Integer id) {
		proyectoBancoService.activarProyectoBanco(id);
		return "redirect:/proyectoBanco/estatusProyectosBanco";
	}
	
	@GetMapping("/publico")
	public String mostrarBancoPublico(
	        @RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
	        Model model) {

	    List<ProyectoBanco> lista = proyectoBancoService.buscarProyectosBancoDisponiblesPorNombre(nombreProyecto);

	    model.addAttribute("proyectosBanco", lista);
	    model.addAttribute("nombreProyecto", nombreProyecto);

	    return "proyectoBanco/publicoProyectoBanco";
	}
	@GetMapping("/residente")
	public String mostrarBancoResidente(
	        @RequestParam(name = "nombreProyecto", required = false) String nombreProyecto,
	        Model model) {

	    List<ProyectoBanco> lista = proyectoBancoService.buscarProyectosDisponiblesParaResidente(nombreProyecto);

	    model.addAttribute("proyectosBanco", lista);
	    model.addAttribute("nombreProyecto", nombreProyecto);

	    return "proyectoBanco/residenteProyectoBanco";
	}
	
}
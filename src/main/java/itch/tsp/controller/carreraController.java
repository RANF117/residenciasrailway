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

import itch.tsp.modelo.Carrera;
import itch.tsp.service.ICarreraService;

@RequestMapping("/carrera")
@Controller
public class carreraController {

	@Autowired
	private ICarreraService carreraService;

	@GetMapping("/mostrarCarreras")
	public String mostrarDatos(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<Carrera> lista = carreraService.buscarCarrerasActivasPorNombre(nombre);
		model.addAttribute("carrera", lista);
		model.addAttribute("nombre", nombre);

		return "carrera/datosCarrera";
	}

	@GetMapping("/estatusCarreras")
	public String mostrarEstatusCarreras(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<Carrera> lista = carreraService.buscarTodasCarrerasPorNombre(nombre);
		model.addAttribute("carrera", lista);
		model.addAttribute("nombre", nombre);

		return "carrera/estatusCarrera";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("carrera", new Carrera());
		return "carrera/formCarrera";
	}

	@PostMapping("/guardar")
	public String guardarCarrera(Carrera carrera, RedirectAttributes redirectAttributes) {

		if (carrera.getEstatus() == null) {
			carrera.setEstatus(1);
		}

		carreraService.guardarCarrera(carrera);
		redirectAttributes.addFlashAttribute("msg", "Carrera guardada correctamente");
		return "redirect:/carrera/mostrarCarreras";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleCarrera(@PathVariable("id") Integer id, Model model) {
		Carrera carrera = carreraService.buscarPorIdCar(id);
		model.addAttribute("carrera", carrera);
		return "carrera/detalleCarrera";
	}

	@GetMapping("/editar/{id}")
	public String editarCarrera(@PathVariable("id") Integer id, Model model) {
		Carrera carrera = carreraService.buscarPorIdCar(id);
		model.addAttribute("carrera", carrera);
		return "carrera/formCarrera";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarCarrera(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		if (carreraService.carreraTieneVinculoActivo(id)) {
			redirectAttributes.addFlashAttribute("msg",
					"No se puede desactivar la carrera porque tiene residentes activos vinculados");
			return "redirect:/carrera/mostrarCarreras";
		}

		carreraService.eliminarCarrera(id);
		redirectAttributes.addFlashAttribute("msg", "Carrera desactivada correctamente");
		return "redirect:/carrera/mostrarCarreras";
	}

	@GetMapping("/activar/{id}")
	public String activarCarrera(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		carreraService.activarCarrera(id);
		redirectAttributes.addFlashAttribute("msg", "Carrera activada correctamente");
		return "redirect:/carrera/estatusCarreras";
	}
}
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

import itch.tsp.modelo.DepartamentoAcademico;
import itch.tsp.service.IDepartamentoAcademicoService;

@RequestMapping("/departamentoAcademico")
@Controller
public class departamentoAcademicoController {

	@Autowired
	private IDepartamentoAcademicoService departamentoService;

	@GetMapping("/mostrarDepartamentosAcademicos")
	public String mostrarDatos(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<DepartamentoAcademico> lista = departamentoService.buscarDepartamentosActivosPorNombre(nombre);
		model.addAttribute("departamentoAcademico", lista);
		model.addAttribute("nombre", nombre);

		return "departamentoAcademico/datosDepartamentoAcademico";
	}

	@GetMapping("/estatusDepartamentosAcademicos")
	public String mostrarEstatusDepartamentos(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<DepartamentoAcademico> lista = departamentoService.buscarTodosDepartamentosPorNombre(nombre);
		model.addAttribute("departamentoAcademico", lista);
		model.addAttribute("nombre", nombre);

		return "departamentoAcademico/estatusDepartamentoAcademico";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("departamentoAcademico", new DepartamentoAcademico());
		return "departamentoAcademico/formDepartamentoAcademico";
	}

	@PostMapping("/guardar")
	public String guardarDepartamento(DepartamentoAcademico departamentoAcademico,
			RedirectAttributes redirectAttributes) {

		if (departamentoAcademico.getEstatus() == null) {
			departamentoAcademico.setEstatus(1);
		}

		departamentoService.guardarDepartamento(departamentoAcademico);
		redirectAttributes.addFlashAttribute("msg", "Departamento académico guardado correctamente");
		return "redirect:/departamentoAcademico/mostrarDepartamentosAcademicos";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleDepartamento(@PathVariable("id") Integer id, Model model) {
		DepartamentoAcademico departamentoAcademico = departamentoService.buscarPorIdDep(id);
		model.addAttribute("departamentoAcademico", departamentoAcademico);
		return "departamentoAcademico/detalleDepartamentoAcademico";
	}

	@GetMapping("/editar/{id}")
	public String editarDepartamento(@PathVariable("id") Integer id, Model model) {
		DepartamentoAcademico departamentoAcademico = departamentoService.buscarPorIdDep(id);
		model.addAttribute("departamentoAcademico", departamentoAcademico);
		return "departamentoAcademico/formDepartamentoAcademico";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarDepartamento(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		if (departamentoService.departamentoTieneVinculoActivo(id)) {
			redirectAttributes.addFlashAttribute("msg",
					"No se puede desactivar el departamento académico porque tiene asesores internos activos vinculados");
			return "redirect:/departamentoAcademico/mostrarDepartamentosAcademicos";
		}

		departamentoService.eliminarDepartamento(id);
		redirectAttributes.addFlashAttribute("msg", "Departamento académico desactivado correctamente");
		return "redirect:/departamentoAcademico/mostrarDepartamentosAcademicos";
	}

	@GetMapping("/activar/{id}")
	public String activarDepartamento(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		departamentoService.activarDepartamento(id);
		redirectAttributes.addFlashAttribute("msg", "Departamento académico activado correctamente");
		return "redirect:/departamentoAcademico/estatusDepartamentosAcademicos";
	}
}
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

import itch.tsp.modelo.Empresa;
import itch.tsp.service.IEmpresaService;

@RequestMapping("/empresa")
@Controller
public class empresaController {

	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/mostrarEmpresas")
	public String mostrarDatos(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<Empresa> lista = empresaService.buscarEmpresasActivasPorNombre(nombre);
		model.addAttribute("empresa", lista);
		model.addAttribute("nombre", nombre);

		return "empresa/datosEmpresa";
	}

	@GetMapping("/estatusEmpresas")
	public String mostrarEstatusEmpresas(
			@RequestParam(name = "nombre", required = false) String nombre,
			Model model) {

		List<Empresa> lista = empresaService.buscarTodasEmpresasPorNombre(nombre);
		model.addAttribute("empresa", lista);
		model.addAttribute("nombre", nombre);

		return "empresa/estatusEmpresa";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa/formEmpresa";
	}

	@PostMapping("/guardar")
	public String guardarEmpresa(Empresa empresa, RedirectAttributes redirectAttributes) {

		if (empresa.getEstatus() == null) {
			empresa.setEstatus(1);
		}

		empresaService.guardarEmpresa(empresa);
		redirectAttributes.addFlashAttribute("msg", "Empresa guardada correctamente");
		return "redirect:/empresa/mostrarEmpresas";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleEmpresa(@PathVariable("id") Integer id, Model model) {
		Empresa empresa = empresaService.buscarPorIdEmp(id);
		model.addAttribute("empresa", empresa);
		return "empresa/detalleEmpresa";
	}

	@GetMapping("/editar/{id}")
	public String editarEmpresa(@PathVariable("id") Integer id, Model model) {
		Empresa empresa = empresaService.buscarPorIdEmp(id);
		model.addAttribute("empresa", empresa);
		return "empresa/formEmpresa";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEmpresa(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		if (empresaService.empresaTieneVinculoActivo(id)) {
			redirectAttributes.addFlashAttribute("msg",
					"No se puede desactivar la empresa porque está vinculada a un asesor externo, propuesta o proyecto activo");
			return "redirect:/empresa/mostrarEmpresas";
		}

		empresaService.eliminarEmpresa(id);
		redirectAttributes.addFlashAttribute("msg", "Empresa desactivada correctamente");
		return "redirect:/empresa/mostrarEmpresas";
	}

	@GetMapping("/activar/{id}")
	public String activarEmpresa(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		empresaService.activarEmpresa(id);
		redirectAttributes.addFlashAttribute("msg", "Empresa activada correctamente");
		return "redirect:/empresa/estatusEmpresas";
	}
}
package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.modelo.Rol;
import itch.tsp.modelo.Usuario;
import itch.tsp.service.IRolService;
import itch.tsp.service.IUsuarioService;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolService rolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/mostrarUsuarios")
	public String mostrarUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.buscarTodosActivos());
		return "usuario/datosUsuario";
	}

	@GetMapping("/estatusUsuarios")
	public String mostrarEstatusUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.buscarTodosUsu());
		return "usuario/estatusUsuario";
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("roles", rolService.buscarTodosActivos());
		model.addAttribute("existeJefeDivision", usuarioService.existeJefeDivisionActivo());
		return "usuario/formUsuario";
	}

	@PostMapping("/guardar")
	public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {

		Usuario existente = null;
		if (usuario.getUsername() != null && !usuario.getUsername().isBlank()) {
			existente = usuarioService.buscarPorUsername(usuario.getUsername());
		}

		if (usuario.getId() == null) {

			if (existente != null) {
				redirectAttributes.addFlashAttribute("msgError", "El nombre de usuario ya existe");
				return "redirect:/usuario/formulario";
			}

			if (usuario.getRol() != null && usuario.getRol().getId() != null) {
				Rol rolSeleccionado = rolService.buscarPorIdRol(usuario.getRol().getId());

				if (rolSeleccionado != null
						&& "JEFE_DIVISION".equalsIgnoreCase(rolSeleccionado.getNombre())
						&& usuarioService.existeJefeDivisionActivo()) {
					redirectAttributes.addFlashAttribute("msgError",
							"Ya existe un jefe de división activo en el sistema");
					return "redirect:/usuario/formulario";
				}

				usuario.setRol(rolSeleccionado);
			}

			if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			} else {
				redirectAttributes.addFlashAttribute("msgError", "La contraseña es obligatoria");
				return "redirect:/usuario/formulario";
			}

			if (usuario.getEstatus() == null) {
				usuario.setEstatus(1);
			}

		} else {
			Usuario usuarioBD = usuarioService.buscarPorIdUsu(usuario.getId());

			if (usuarioBD == null) {
				redirectAttributes.addFlashAttribute("msgError", "Usuario no encontrado");
				return "redirect:/usuario/mostrarUsuarios";
			}

			if (existente != null && !existente.getId().equals(usuario.getId())) {
				redirectAttributes.addFlashAttribute("msgError", "El nombre de usuario ya existe");
				return "redirect:/usuario/editar/" + usuario.getId();
			}

			if (usuario.getRol() != null && usuario.getRol().getId() != null) {
				Rol rolSeleccionado = rolService.buscarPorIdRol(usuario.getRol().getId());

				if (rolSeleccionado != null
						&& "JEFE_DIVISION".equalsIgnoreCase(rolSeleccionado.getNombre())
						&& usuarioBD.getRol() != null
						&& !"JEFE_DIVISION".equalsIgnoreCase(usuarioBD.getRol().getNombre())
						&& usuarioService.existeJefeDivisionActivo()) {
					redirectAttributes.addFlashAttribute("msgError",
							"Ya existe un jefe de división activo en el sistema");
					return "redirect:/usuario/editar/" + usuario.getId();
				}

				usuario.setRol(rolSeleccionado);
			} else {
				usuario.setRol(usuarioBD.getRol());
			}

			if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
				usuario.setPassword(usuarioBD.getPassword());
			} else {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}

			if (usuario.getEstatus() == null) {
				usuario.setEstatus(usuarioBD.getEstatus());
			}
		}

		usuarioService.guardarUsuario(usuario);
		redirectAttributes.addFlashAttribute("msg", "Usuario guardado correctamente");
		return "redirect:/usuario/mostrarUsuarios";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model model) {
		Usuario usuario = usuarioService.buscarPorIdUsu(id);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles", rolService.buscarTodosActivos());
		model.addAttribute("existeJefeDivision", usuarioService.existeJefeDivisionActivo());
		return "usuario/formUsuario";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleUsuario(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("usuario", usuarioService.buscarPorIdUsu(id));
		return "usuario/detalleUsuario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		usuarioService.eliminarUsuario(id);
		redirectAttributes.addFlashAttribute("msg", "Usuario desactivado correctamente");
		return "redirect:/usuario/mostrarUsuarios";
	}

	@GetMapping("/activar/{id}")
	public String activarUsuario(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		usuarioService.activarUsuario(id);
		redirectAttributes.addFlashAttribute("msg", "Usuario activado correctamente");
		return "redirect:/usuario/estatusUsuarios";
	}
}
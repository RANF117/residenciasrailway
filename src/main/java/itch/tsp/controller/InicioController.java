package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import itch.tsp.modelo.AsesorExterno;
import itch.tsp.modelo.AsesorInterno;
import itch.tsp.modelo.Residente;
import itch.tsp.modelo.Usuario;
import itch.tsp.repository.AsesorExternoRepository;
import itch.tsp.repository.AsesorInternoRepository;
import itch.tsp.repository.ResidenteRepository;
import itch.tsp.repository.UsuarioRepository;

@Controller
public class InicioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ResidenteRepository residenteRepository;

	@Autowired
	private AsesorInternoRepository asesorInternoRepository;

	@Autowired
	private AsesorExternoRepository asesorExternoRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/inicio")
	public String inicio(Authentication authentication, Model model) {
		if (authentication != null) {
			String username = authentication.getName();
			model.addAttribute("username", username);

			String rolActual = authentication.getAuthorities().stream()
					.map(a -> a.getAuthority())
					.filter(r -> r.startsWith("ROLE_"))
					.findFirst()
					.orElse("SIN_ROL")
					.replace("ROLE_", "")
					.replace("_", " ");

			model.addAttribute("rolActual", rolActual);

			String nombreCompleto = username;

			Usuario usuario = usuarioRepository.findByUsernameAndEstatus(username, 1);

			if (usuario != null && usuario.getRol() != null) {
				String rol = usuario.getRol().getNombre();

				if ("JEFE_DIVISION".equalsIgnoreCase(rol)) {
					nombreCompleto = "Aldo Oliva Herrera";
				} else if ("RESIDENTE".equalsIgnoreCase(rol)) {
					Residente residente = residenteRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);
					if (residente != null) {
						nombreCompleto = residente.getNombre() + " " +
								residente.getApellidoPaterno() + " " +
								residente.getApellidoMaterno();
					}
				} else if ("ASESOR_INTERNO".equalsIgnoreCase(rol)) {
					AsesorInterno asesor = asesorInternoRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);
					if (asesor != null) {
						nombreCompleto = asesor.getNombre() + " " +
								asesor.getApellidoPaterno() + " " +
								asesor.getApellidoMaterno();
					}
				} else if ("ASESOR_EXTERNO".equalsIgnoreCase(rol)) {
					AsesorExterno asesor = asesorExternoRepository.findByUsuario_IdAndEstatus(usuario.getId(), 1);
					if (asesor != null) {
						nombreCompleto = asesor.getNombre() + " " +
								asesor.getApellidoPaterno() + " " +
								asesor.getApellidoMaterno();
					}
				}
			}

			model.addAttribute("nombreCompleto", nombreCompleto);
		}

		return "inicio";
	}
}
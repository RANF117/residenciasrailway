package itch.tsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/modulos")
@Controller
public class ModulosController {

	@GetMapping("/administrarProyectosResidencia")
	public String administrarProyectosResidencia() {
		return "modulos/administrarProyectosResidencia";
	}
}
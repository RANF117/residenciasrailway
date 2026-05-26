package itch.tsp.config;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrearDirectoriosConfig {

	@Bean
	CommandLineRunner crearDirectorios() {
		return args -> {
			String raiz = "C:" + File.separator + "residencias";

			File carpetaRaiz = new File(raiz);
			File carpetaResidentes = new File(raiz + File.separator + "residentes");
			File carpetaAsesoresInternos = new File(raiz + File.separator + "asesoresInternos");
			File carpetaAsesoresExternos = new File(raiz + File.separator + "asesoresExternos");
			File carpetaPdf = new File(raiz + File.separator + "pdf");

			if (!carpetaRaiz.exists()) {
				carpetaRaiz.mkdirs();
			}

			if (!carpetaResidentes.exists()) {
				carpetaResidentes.mkdirs();
			}

			if (!carpetaAsesoresInternos.exists()) {
				carpetaAsesoresInternos.mkdirs();
			}

			if (!carpetaAsesoresExternos.exists()) {
				carpetaAsesoresExternos.mkdirs();
			}

			if (!carpetaPdf.exists()) {
				carpetaPdf.mkdirs();
			}

			System.out.println("Directorios de residencias verificados correctamente");
		};
	}
}
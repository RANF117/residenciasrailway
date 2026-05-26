package itch.tsp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(usuarioDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authenticationProvider(authenticationProvider())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/login", "/css/**", "/img/**", "/js/**", "/proyectoBanco/publico").permitAll()

				.requestMatchers("/inicio").authenticated()

				// USUARIOS Y CATÁLOGOS
				.requestMatchers("/usuario/**").hasRole("JEFE_DIVISION")
				.requestMatchers("/empresa/**", "/carrera/**", "/departamentoAcademico/**").hasRole("JEFE_DIVISION")

				// ACTORES
				.requestMatchers("/residente/**").hasRole("JEFE_DIVISION")
				.requestMatchers("/asesorInterno/**").hasRole("JEFE_DIVISION")
				.requestMatchers("/asesorExterno/**").hasRole("JEFE_DIVISION")

				// BANCO DE PROYECTOS
				.requestMatchers("/proyectoBanco/publico").permitAll()
				
				.requestMatchers("/proyectoBanco/mostrarProyectosBanco", "/proyectoBanco/estatusProyectosBanco").hasRole("JEFE_DIVISION")
				.requestMatchers("/proyectoBanco/ver/**").hasAnyRole("JEFE_DIVISION", "RESIDENTE")
				
				.requestMatchers("/proyectoBanco/formulario", "/proyectoBanco/guardar/**", "/proyectoBanco/editar/**", "/proyectoBanco/eliminar/**", "/proyectoBanco/activar/**").hasRole("JEFE_DIVISION")
				.requestMatchers("/proyectoBanco/residente").hasRole("RESIDENTE")

				// PROPUESTA DE PROYECTO
				.requestMatchers("/propuestaProyecto/mostrarPropuestas", "/propuestaProyecto/ver/**").hasRole("RESIDENTE")
				.requestMatchers("/propuestaProyecto/formulario", "/propuestaProyecto/guardar/**", "/propuestaProyecto/editar/**", "/propuestaProyecto/eliminar/**").hasRole("RESIDENTE")
				.requestMatchers("/propuestaProyecto/revisar/**").hasRole("JEFE_DIVISION")

				// PROYECTO DE RESIDENCIA
				.requestMatchers("/proyectoResidencia/**").hasRole("JEFE_DIVISION")

				// REPORTE PRELIMINAR
				.requestMatchers("/reportePreliminar/mostrarReportes", "/reportePreliminar/ver/**").hasAnyRole("RESIDENTE", "JEFE_DIVISION")
				.requestMatchers("/reportePreliminar/formulario", "/reportePreliminar/guardar/**", "/reportePreliminar/editar/**", "/reportePreliminar/eliminar/**").hasRole("RESIDENTE")
				.requestMatchers("/reportePreliminar/revisar/**").hasRole("JEFE_DIVISION")

				// DOCUMENTOS DE RESIDENCIA
				.requestMatchers("/documentoResidencia/mostrarDocumentos", "/documentoResidencia/ver/**").hasAnyRole("RESIDENTE", "ASESOR_INTERNO", "ASESOR_EXTERNO", "JEFE_DIVISION")
				.requestMatchers("/documentoResidencia/formulario", "/documentoResidencia/guardar/**", "/documentoResidencia/editar/**", "/documentoResidencia/eliminar/**").hasRole("RESIDENTE")

				// SEGUIMIENTOS
				.requestMatchers("/seguimientoResidencia/mostrarSeguimientos", "/seguimientoResidencia/ver/**").hasAnyRole("ASESOR_INTERNO", "ASESOR_EXTERNO", "JEFE_DIVISION")
				.requestMatchers("/seguimientoResidencia/formulario", "/seguimientoResidencia/guardar/**", "/seguimientoResidencia/editar/**", "/seguimientoResidencia/eliminar/**").hasAnyRole("ASESOR_INTERNO", "ASESOR_EXTERNO")

				// EVALUACIÓN REPORTE FINAL
				.requestMatchers("/evaluacionReporteFinal/mostrar", "/evaluacionReporteFinal/ver/**").hasAnyRole("ASESOR_INTERNO", "ASESOR_EXTERNO", "JEFE_DIVISION")
				.requestMatchers("/evaluacionReporteFinal/formulario", "/evaluacionReporteFinal/guardar/**", "/evaluacionReporteFinal/editar/**", "/evaluacionReporteFinal/eliminar/**").hasAnyRole("ASESOR_INTERNO", "ASESOR_EXTERNO")

				// CÁLCULO FINAL Y LIBERACIÓN
				.requestMatchers("/proyectoResidencia/calcularFinal/**").hasRole("JEFE_DIVISION")
				.requestMatchers("/proyectoResidencia/liberacion/**").hasRole("ASESOR_INTERNO")

				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/inicio", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			);

		return http.build();
	}
}
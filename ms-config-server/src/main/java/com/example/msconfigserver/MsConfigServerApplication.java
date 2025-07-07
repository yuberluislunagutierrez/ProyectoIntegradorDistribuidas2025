package com.example.msconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// Habilita este proyecto como servidor de configuración
@EnableConfigServer
@SpringBootApplication // Marca esta clase como una aplicación Spring Boot
public class MsConfigServerApplication {
	/**
	 * Al ejecutarse, arranca un servidor en el puerto definido en application.yml (por defecto 7070),
	 * habilita el servidor de configuración y queda listo para responder peticiones
	 * de configuración desde otros microservicios.
	 */

	public static void main(String[] args) {
		SpringApplication.run(MsConfigServerApplication.class, args);
	}

}

package mca.filesmanagement.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class BpmApp {

	/**
	 * Método de entrada a la aplicación.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BpmApp.class, args);
	}

	/**
	 * PostConstruct.
	 */
	public void postConstruct() {
	}
}

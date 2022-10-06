package com.treblle.spring.example;

import com.treblle.spring.annotation.EnableTreblle;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableTreblle
@SpringBootApplication
public class TreblleSpringBootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreblleSpringBootExampleApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("JWT",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
						.addSecuritySchemes("HTTPBasic",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info().title("Treblle Spring Boot API boilerplate").version("1.0"));
	}

}

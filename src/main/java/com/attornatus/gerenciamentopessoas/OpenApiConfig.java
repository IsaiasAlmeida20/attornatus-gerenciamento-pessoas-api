package com.attornatus.gerenciamentopessoas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	 @Bean
	 public OpenAPI customOpenApi() {
		 return new OpenAPI()
				 .components(new Components())
				 .info(new Info()
						 .title("Attornatus management person API")
						 .description("Documentation Api management person attornatus"));
	 }
	 
}

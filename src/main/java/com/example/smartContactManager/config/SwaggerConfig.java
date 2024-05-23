package com.example.smartContactManager.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    //http://localhost:8080/swagger-ui.html
    //http://localhost:8080/swagger-ui/index.html
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title("SmartContactManager APIS").version("Version 1"))
                .addSecurityItem(new SecurityRequirement()
                        .addList("Authorization"))
                .components(
                        new Components()
                                .addSecuritySchemes("Authorization", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                );
    }
}
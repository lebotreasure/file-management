package com.enviro.assessment.grad001.lebohangkhaeane.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Lebohang",
                        email = "lebohangsylviak@gmail.com"
                ),
                description = "OpenApi Documentation for environmental data for analysis for Enviro365",
                title = "OpenApi specification - Lebohang",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}

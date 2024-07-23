package com.kosign.bill24.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        info = @Info(
                // contact = @Contact( name = "", email = "" ),
                title = "Bill24",
                version = "1.0",
                description = "Bill24 API - Project Managements",
                // license = @License( name = "", url = "" ),
                termsOfService = "Terms of service"
        ),
        servers = {
                // @Server( description = "Local Environment", url = "http://localhost:4141" ),
                // @Server( description = "PROD Environment", url = "" )
        }
)

public class OpenAPIConfiguration {

}

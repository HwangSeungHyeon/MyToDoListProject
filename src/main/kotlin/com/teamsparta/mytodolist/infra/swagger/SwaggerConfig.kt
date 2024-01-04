package com.teamsparta.mytodolist.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI() //OpenAPI 타입의 Bean을 재정의
        .components(Components()
            .addSecuritySchemes(HttpHeaders.AUTHORIZATION, SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .addSecurityItem(SecurityRequirement().addList("JWT"))

        .info(
            Info()
                .title("Todo List API")
                .description("Todo List schema")
                .version("1.0.0")
        )
}
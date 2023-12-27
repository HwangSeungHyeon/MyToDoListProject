package com.teamsparta.mytodolist.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI() //OpenAPI 타입의 Bean을 재정의
        .components(Components())
        .info(
            Info()
                .title("Todo List API")
                .description("Todo List schema")
                .version("1.0.0")
        )
}
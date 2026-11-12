
package com.example.department;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI departmentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Department Service API")
                        .description("REST APIs for managing departments")
                        .version("1.0.0"));
    }
}


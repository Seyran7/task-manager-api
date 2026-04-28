package com.seyran.taskmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "bearepackage com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}package com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}package com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}package com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}package com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}package com.seyran.taskmanager.config;\n" +
                "\n" +
                "import io.swagger.v3.oas.models.OpenAPI;\n" +
                "import io.swagger.v3.oas.models.Components;\n" +
                "import io.swagger.v3.oas.models.info.Info;\n" +
                "import io.swagger.v3.oas.models.security.SecurityRequirement;\n" +
                "import io.swagger.v3.oas.models.security.SecurityScheme;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "public class OpenApiConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public OpenAPI customOpenAPI() {\n" +
                "\n" +
                "        final String securitySchemeName = \"bearerAuth\";\n" +
                "\n" +
                "        return new OpenAPI()\n" +
                "                .info(new Info()\n" +
                "                        .title(\"Task Manager\")\n" +
                "                        .version(\"1.0\")\n" +
                "                        .description(\"Spring Boot Task Management System\"))\n" +
                "\n" +
                "                // \uD83D\uDD25 JWT əlavə etdik\n" +
                "                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))\n" +
                "\n" +
                "                .components(new Components()\n" +
                "                        .addSecuritySchemes(securitySchemeName,\n" +
                "                                new SecurityScheme()\n" +
                "                                        .name(securitySchemeName)\n" +
                "                                        .type(SecurityScheme.Type.HTTP)\n" +
                "                                        .scheme(\"bearer\")\n" +
                "                                        .bearerFormat(\"JWT\")\n" +
                "                        )\n" +
                "                );\n" +
                "    }\n" +
                "}rAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager")
                        .version("1.0")
                        .description("Spring Boot Task Management System"))

                // 🔥 JWT əlavə etdik
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
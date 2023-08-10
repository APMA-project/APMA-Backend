package APMA.APMAproject.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi group1() {
        return GroupedOpenApi.builder()
                .group("ALL")
                .pathsToMatch("/**")
                // .packagesToScan("com.example.swagger") // package 필터 설정
                .build();
    }

    @Bean
    public GroupedOpenApi group2() {
        return GroupedOpenApi.builder()
                .group("ADMIN")
                .pathsToMatch("/APMA/admin/**","/APMA/member/**")
                // .packagesToScan("com.example.swagger") // package 필터 설정
                .build();
    }

    @Bean
    public GroupedOpenApi group3() {
        return GroupedOpenApi.builder()
                .group("MEMBER")
                .pathsToMatch("/APMA/member/**")
                .build();
    }

    @Bean
    public GroupedOpenApi group4() {
        return GroupedOpenApi.builder()
                .group("JOIN")
                .pathsToMatch("/APMA/join/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {

        io.swagger.v3.oas.models.info.Info info = new Info()
                .version("v1.0.0")
                .title("PROJECT APMA API Document");
//                .description("API Description");

        // SecuritySecheme명
        String jwtSchemeName = "JWT Token";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}

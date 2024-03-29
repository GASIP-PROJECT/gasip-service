package com.example.gasip.global.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@OpenAPIDefinition(
        info = @Info(title = "Gasip 서비스 API 명세서",
                description = "Gasip 교수평가 서비스 API 명세서",
                version = "v1.0.0"))
@RequiredArgsConstructor
@Configuration
public class swaggerConfig {

    /**
     * 전체 API
     */
    @Bean
    public GroupedOpenApi gasipOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Gasip 서비스 V1")
                .pathsToMatch(paths)
                .build();
    }

    /**
     * JWT 관련 설정
     */
    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("BearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("BearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement));
    }


//    /**
//     * 교수 정보 그룹
//     */
//    @Bean
//    public GroupedOpenApi professorOpenApi() {
//        String[] paths = {"/all-professors/**"};
//
//        return GroupedOpenApi.builder()
//                .group("professors")
//                .pathsToMatch(paths)
//                .build();
//    }
//
//    /**
//     * 교수 상세정보 그룹
//     */
//    @Bean
//    public GroupedOpenApi professorDetailsOpenApi() {
//        String[] paths = {"/professors/**"};
//
//        return GroupedOpenApi.builder()
//                .group("professorDetails")
//                .pathsToMatch(paths)
//                .build();
//    }
//
//    /**
//     * 게시글 CRUD 그룹
//     */
//    @Bean
//    public GroupedOpenApi boardOpenApi() {
//        String[] paths = {"/boards/**"};
//
//        return GroupedOpenApi.builder()
//                .group("boards")
//                .pathsToMatch(paths)
//                .build();
//    }
//
//    /**
//     * 댓글 그룹
//     */
//    @Bean
//    public GroupedOpenApi commentOpenApi() {
//        String[] paths = {"/comments/**"};
//
//        return GroupedOpenApi.builder()
//                .group("comment")
//                .pathsToMatch(paths)
//                .build();
//    }
//
//    /**
//     * 멤버 그룹
//     */
//    @Bean
//    public GroupedOpenApi memberOpenApi() {
//        String[] paths = {"/members/**"};
//
//        return GroupedOpenApi.builder()
//            .group("member")
//            .pathsToMatch(paths)
//            .build();
//    }
//

}

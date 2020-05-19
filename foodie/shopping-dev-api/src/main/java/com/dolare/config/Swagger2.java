package com.dolare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    // /swagger-ui.html   default path
    // third party doc.html
    // core configuration docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // the type of api doc is swagger2
            .apiInfo(apiInfo()) // define api doc summary
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.dolare.controller"))
            .paths(PathSelectors.any())
            .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("dolare foodie API documentation")
                .contact(new Contact("dolare", "www.google.com", "dolare@gmail.com"))
                .description("TBA")
                .version("1.0.0")
                .termsOfServiceUrl("www.dolare.com")
                .build();
    }
}

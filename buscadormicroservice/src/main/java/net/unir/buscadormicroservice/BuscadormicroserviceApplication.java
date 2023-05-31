package net.unir.buscadormicroservice;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@EnableEurekaClient
//@EntityScan({"net.unir.entity"})
@SpringBootApplication
public class BuscadormicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuscadormicroserviceApplication.class, args);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("unir-apis")
                .packagesToScan("net.unir")
                //.pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {

        OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info()
                .title("Buscador service API")
                .description("Buscador Service API reference for developers, teh error handler is implement using \n"
                        + " The IETF devised RFC 7807 effor, which creates a generalized error-handling schema.\n"
                        + "https://tools.ietf.org/html/rfc7807")
                .version("v0.0.1")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"))
                .contact(new Contact()
                        .name("Fabián Millán")
                        .email("fabiandresmillanj@gmail.com")
                        .url("https://ziggza.com")));

        openAPI.externalDocs(new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));

        //openAPI.components(new Components());

        return openAPI;
    }

}

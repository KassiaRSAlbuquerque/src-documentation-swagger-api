package io.github.kassiarsalbuquerque.VendasSpringWeb.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		
		//DOCUMENTACAO SEM SEGURANCA
//		return new Docket(DocumentationType.SWAGGER_2)
//				.useDefaultResponseMessages(false)
//				.select()
//				.apis(RequestHandlerSelectors
//						.basePackage("io.github.kassiarsalbuquerque.VendasSpringWeb.controller"))
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
		
		//DOCUMENTACAO COM SEGURANCA
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("io.github.kassiarsalbuquerque.VendasSpringWeb.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Arrays.asList(securityContext()))
				.apiInfo(apiInfo());
	}
	
	private Contact contact() {
		return new Contact("Kassia Albuquerque", "https://github.com/KassiaRSAlbuquerque", "krsa@gmail.com");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Vendas API")
				.description("API do projeto de Vendas usando segurança")
				.version("1.0")
				.contact(contact())
				.build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(dafaultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}
	
	private List<SecurityReference> dafaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] scopes = new AuthorizationScope[1];
		scopes[0] = authorizationScope;
		
		SecurityReference reference = new SecurityReference("JWT", scopes);
		List<SecurityReference> auths = new ArrayList<SecurityReference>();
		auths.add(reference);
		
		return auths;
	}
}
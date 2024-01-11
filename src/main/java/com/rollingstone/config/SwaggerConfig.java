package com.rollingstone.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {

	//not working-->javax.servlet.http.HttpServletRequest not present
	//using jakarta in latest version of spring boot instead of javax
	
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build(); }
	 */
	
}

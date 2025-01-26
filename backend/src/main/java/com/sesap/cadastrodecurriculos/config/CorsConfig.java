package com.sesap.cadastrodecurriculos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
	.allowedOrigins("http://localhost","http://localhost:5500","http://192.168.0.20:5500", "http://localhost:80")
	.allowedMethods("GET","POST")
	.allowedHeaders("*")
	.allowCredentials(true);
    }
}

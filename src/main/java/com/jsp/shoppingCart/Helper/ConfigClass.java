package com.jsp.shoppingCart.Helper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages ="com")
public class ConfigClass {
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setSuffix(".jsp"); //here i write .jsp so i can use both servlets and hibernate also.
		resolver.setPrefix("/");
		return resolver;
	}
	@Bean
	public EntityManagerFactory getEmf() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");		
		return emf;
}
}

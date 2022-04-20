package com.ty.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ty.dto","com.ty.dao","com.ty.controller","com.ty.service"})
public class HungryHelpersConfiguration {
	@Bean
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
		return entityManagerFactory.createEntityManager();
	}
	@Bean
	public EntityTransaction getEntityTransaction() {
		return getEntityManager().getTransaction();
	}
}

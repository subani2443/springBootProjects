package com.TejaITB2.Springboot;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = "com.TejaITB2.*")
@EnableJpaRepositories("com.TejaITB2.*")
@EntityScan("com.TejaITB2.*")
public class TejaItb2SpringbootApplication {
	private static final Logger logger = LogManager.getLogger(TejaItb2SpringbootApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TejaItb2SpringbootApplication.class, args);
		System.out.println("hello subani");
		logger.info("info method");
		logger.warn("warn");
		logger.debug("debug");
		logger.error("error");
		logger.trace("trace");
		logger.fatal("fatal");
		logger.debug("debug msg");
	}

}

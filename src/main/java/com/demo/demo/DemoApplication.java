package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
			System.setProperty("DB_URL", dotenv.get("DB_URL", "jdbc:postgresql://localhost:5432/demo"));
			System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME", "demo"));
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD", "demo"));
		} catch (Exception e) {
			System.out.println("Warning: Could not load .env file: " + e.getMessage());
			// Set default values if .env file is not available
			System.setProperty("DB_URL", "jdbc:postgresql://localhost:5432/demo");
			System.setProperty("DB_USERNAME", "demo");
			System.setProperty("DB_PASSWORD", "demo");
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}

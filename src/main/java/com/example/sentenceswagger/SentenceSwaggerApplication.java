package com.example.sentenceswagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sentence Evaluation API", description = "This Api evaluates sentences for correctness as per defined rules."))
public class SentenceSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentenceSwaggerApplication.class, args);
	}

}

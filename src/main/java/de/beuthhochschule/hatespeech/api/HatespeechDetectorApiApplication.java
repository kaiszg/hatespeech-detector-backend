package de.beuthhochschule.hatespeech.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HatespeechDetectorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HatespeechDetectorApiApplication.class, args);
	}
}

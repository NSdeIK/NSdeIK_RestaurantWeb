package hu.inf.unideb.NSdeIK_RestaurantWeb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NSdeIkRestaurantWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(NSdeIkRestaurantWebApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

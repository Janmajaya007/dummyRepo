package com.janmajaya.Movie_Booking_Application;

import com.janmajaya.Movie_Booking_Application.filter.JWTFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition
public class MovieBookingApplication {

	@Bean
	public FilterRegistrationBean jwtFilterBean()
	{
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		fb.addUrlPatterns("/api/v1/**");
		return fb;

	}
	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApplication.class, args);
		log.info("moviebooking microservice is running");
	}

}

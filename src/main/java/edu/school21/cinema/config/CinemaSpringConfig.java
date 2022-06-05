package edu.school21.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("edu.school21.cinema")
@EnableWebMvc
public class CinemaSpringConfig {
}

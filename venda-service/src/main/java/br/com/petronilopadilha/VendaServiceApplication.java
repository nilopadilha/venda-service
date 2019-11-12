package br.com.petronilopadilha;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EntityScan(basePackages = {"br.com.petronilopadilha.model"})
@ComponentScan(basePackages = {"br.com.petronilopadilha.*"})
@EnableJpaRepositories(basePackages = {"br.com.petronilopadilha.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class VendaServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(VendaServiceApplication.class, args);
	}

}

package cz.ucl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"cz.ucl.*"})
@EntityScan(basePackages = {"cz.ucl.*"})
@EnableJpaRepositories(basePackages = {"cz.ucl.*"})
public class EShopApp {

    public static void main(String[] args) {
        SpringApplication.run(EShopApp.class);
    }
}

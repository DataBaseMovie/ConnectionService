package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class ConnexionApplication implements CommandLineRunner {
    private final ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());

        return resolver;
    }
    public ConnexionApplication( ThymeleafProperties properties) {
        this.properties = properties;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnexionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Connexion");
    }
}

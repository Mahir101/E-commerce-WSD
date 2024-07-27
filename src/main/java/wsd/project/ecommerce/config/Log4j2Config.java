package wsd.project.ecommerce.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Log4j2Config {

    @Value("${project.name}")
    private String projectName;

    @PostConstruct
    public void init() {
        System.setProperty("project.name", projectName);
    }
}


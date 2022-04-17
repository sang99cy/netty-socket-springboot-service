package com.quangsang.springbaseexample;

/*import com.quangsang.springbaseexample.audit.AuditorAwareImpl;*/
import com.quangsang.springbaseexample.task.SendAPITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
/*@EnableJpaAuditing(auditorAwareRef = "auditorAware")*/
public class SpringBaseExampleApplication {
    @Autowired
    private RestTemplate restTemplate;

   /* @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(SpringBaseExampleApplication.class, args);
    }
}

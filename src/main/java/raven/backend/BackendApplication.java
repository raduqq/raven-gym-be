package raven.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import raven.backend.security.auth.AuthenticationService;
import raven.backend.security.auth.RegisterRequest;

import static raven.backend.security.user.Role.ADMIN;
import static raven.backend.security.user.Role.MANAGER;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = new RegisterRequest(
                    "Admin",
                    "Admin",
                    "admin@gmail.com",
                    "password",
                    ADMIN
            );
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = new RegisterRequest(
                    "Manager",
                    "Manager",
                    "manager@gmail.com",
                    "password",
                    MANAGER
            );
            System.out.println("Manager token: " + service.register(manager).getAccessToken());
        };
    }
}

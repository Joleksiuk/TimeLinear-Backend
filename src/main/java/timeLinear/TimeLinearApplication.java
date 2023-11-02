package timeLinear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import timeLinear.auth.AuthenticationService;
import timeLinear.auth.RegisterRequest;

import static timeLinear.models.auth.Role.*;

@SpringBootApplication
public class TimeLinearApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeLinearApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080", "http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticationService service
//    ) {
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .username("Admin")
//                    .email("admin@mail.com")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//            var manager = RegisterRequest.builder()
//                    .username("Admin")
//                    .email("manager@mail.com")
//                    .password("password")
//                    .role(MANAGER)
//                    .build();
//            System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//            var user1 = RegisterRequest.builder()
//                    .username("user1")
//                    .email("user1@mail.com")
//                    .password("user1")
//                    .role(USER)
//                    .build();
//            System.out.println("USER1 token: " + service.register(user1).getAccessToken());
//            var user2 = RegisterRequest.builder()
//                    .username("user2")
//                    .email("user2@mail.com")
//                    .password("user2")
//                    .role(USER)
//                    .build();
//            System.out.println("USER2 token: " + service.register(user1).getAccessToken());
//            var user3 = RegisterRequest.builder()
//                    .username("user3")
//                    .email("user3@mail.com")
//                    .password("user3")
//                    .role(USER)
//                    .build();
//            System.out.println("USER3 token: " + service.register(user1).getAccessToken());
//
//        };
//    }

}

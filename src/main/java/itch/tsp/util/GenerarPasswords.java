/*package itch.tsp.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class GenerarPasswords implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public GenerarPasswords(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("jefe123 -> " + passwordEncoder.encode("jefe123"));
        System.out.println("resi123 -> " + passwordEncoder.encode("resi123"));
        System.out.println("interno123 -> " + passwordEncoder.encode("interno123"));
        System.out.println("externo123 -> " + passwordEncoder.encode("externo123"));
    }
}*/
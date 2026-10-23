package com.ckennedy.api.config;

import com.ckennedy.api.look.LookRepository;
import com.ckennedy.api.product.ProductRepository;
import com.ckennedy.api.user.Role;
import com.ckennedy.api.user.UserEntity;
import com.ckennedy.api.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Initialise le compte administrateur au démarrage et supprime les anciennes
 * données de démonstration si elles sont encore présentes en base.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final List<String> DEMO_PRODUCT_IDS = List.of(
            "ckennedy-equinox-lycra",
            "ckennedy-tactical-sand-hoodie",
            "ckennedy-tactical-cargo",
            "ckennedy-cybernetic-jacket"
    );

    private static final List<String> DEMO_LOOK_IDS = List.of(
            "look-01",
            "look-02",
            "look-03"
    );

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LookRepository lookRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            ProductRepository productRepository,
            LookRepository lookRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.lookRepository = lookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        ensureAdminUser();
        removeDemoData();
    }

    private void ensureAdminUser() {
        String envEmail = System.getenv("CKENNEDY_ADMIN_EMAIL");
        String envPassword = System.getenv("CKENNEDY_ADMIN_PASSWORD");

        String adminEmail = (envEmail != null && !envEmail.isBlank())
                ? envEmail.trim().toLowerCase()
                : "admin@ckennedy.com";
        String adminPassword = (envPassword != null && !envPassword.isBlank())
                ? envPassword
                : "Ck-dev-2026!";

        UserEntity admin = userRepository.findByEmailIgnoreCase(adminEmail)
                .orElseGet(UserEntity::new);

        admin.setName("Propriétaire C'Kennedy");
        admin.setEmail(adminEmail);
        admin.setPasswordHash(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }

    private void removeDemoData() {
        for (String lookId : DEMO_LOOK_IDS) {
            if (lookRepository.existsById(lookId)) {
                lookRepository.deleteById(lookId);
            }
        }
        for (String productId : DEMO_PRODUCT_IDS) {
            if (productRepository.existsById(productId)) {
                productRepository.deleteById(productId);
            }
        }
    }
}

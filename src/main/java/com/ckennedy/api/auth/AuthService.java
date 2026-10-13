package com.ckennedy.api.auth;

import com.ckennedy.api.exception.EmailAlreadyUsedException;
import com.ckennedy.api.exception.InvalidCredentialsException;
import com.ckennedy.api.generated.model.AuthResponse;
import com.ckennedy.api.generated.model.LoginRequest;
import com.ckennedy.api.generated.model.RegisterRequest;
import com.ckennedy.api.security.JwtService;
import com.ckennedy.api.user.Role;
import com.ckennedy.api.user.UserEntity;
import com.ckennedy.api.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Transactional
    public AuthResponse register(
            RegisterRequest request
    ) {

        String email = request
                .getEmail()
                .trim()
                .toLowerCase();

        if (
                userRepository
                        .existsByEmailIgnoreCase(email)
        ) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = new UserEntity();

        user.setName(
                request.getName().trim()
        );

        user.setEmail(email);

        user.setPasswordHash(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        // L'utilisateur ne choisit jamais son rôle.
        user.setRole(Role.CLIENT);

        UserEntity savedUser =
                userRepository.save(user);

        String token =
                jwtService.generateToken(savedUser);

        AuthResponse response =
                new AuthResponse();

        response.setToken(token);
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        response.setRole(
                AuthResponse.RoleEnum.CLIENT
        );

        return response;
    }

    public AuthResponse login(LoginRequest request) {

        String email = request
                .getEmail()
                .trim()
                .toLowerCase();

        UserEntity user = userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(InvalidCredentialsException::new);

        boolean passwordCorrect =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPasswordHash()
                );

        if (!passwordCorrect) {
            throw new InvalidCredentialsException();
        }

        String token =
                jwtService.generateToken(user);

        AuthResponse response =
                new AuthResponse();

        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setName(user.getName());

        response.setRole(
                AuthResponse.RoleEnum.valueOf(
                        user.getRole().name()
                )
        );

        return response;
    }
}

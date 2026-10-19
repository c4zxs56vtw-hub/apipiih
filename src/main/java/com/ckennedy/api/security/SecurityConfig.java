package com.ckennedy.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization", "Content-Disposition"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exceptions ->
                        exceptions
                                .authenticationEntryPoint(
                                        (request, response, exception) -> {
                                            response.setStatus(401);
                                            response.setContentType("application/json");
                                            response.setCharacterEncoding("UTF-8");
                                            response.getWriter().write(
                                                    """
                                                    {
                                                      "message": "Authentification requise."
                                                    }
                                                    """
                                            );
                                        }
                                )
                                .accessDeniedHandler(
                                        (request, response, exception) -> {
                                            response.setStatus(403);
                                            response.setContentType("application/json");
                                            response.setCharacterEncoding("UTF-8");
                                            response.getWriter().write(
                                                    """
                                                    {
                                                      "message": "Accès réservé aux administrateurs."
                                                    }
                                                    """
                                            );
                                        }
                                )
                )

                .authorizeHttpRequests(auth ->
                        auth
                                // OPTIONS PREFLIGHT
                                .requestMatchers(
                                        HttpMethod.OPTIONS,
                                        "/**"
                                )
                                .permitAll()

                                // AUTH
                                .requestMatchers(
                                        "/api/auth/register",
                                        "/api/auth/login",
                                        "/api/auth/logout"
                                )
                                .permitAll()

                                // TRENDING PUBLIC
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/products/trending"
                                )
                                .permitAll()

                                // LIKE / UNLIKE PUBLICS
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/products/*/like"
                                )
                                .permitAll()

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/products/*/like"
                                )
                                .permitAll()

                                // LOOKS PUBLICS
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/looks/**"
                                )
                                .permitAll()

                                // UPLOADS PUBLICS
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/uploads/**"
                                )
                                .permitAll()

                                // CREATION LOOK ADMIN
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/looks"
                                )
                                .hasRole("ADMIN")

                                // MODIFICATION LOOK ADMIN
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/looks/**"
                                )
                                .hasRole("ADMIN")

                                // SUPPRESSION LOOK ADMIN
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/looks/**"
                                )
                                .hasRole("ADMIN")

                                // UPLOAD ADMIN
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/upload"
                                )
                                .hasRole("ADMIN")

                                // CREATION PRODUIT ADMIN
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/products"
                                )
                                .hasRole("ADMIN")

                                // MODIFICATION PRODUIT ADMIN
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/products/**"
                                )
                                .hasRole("ADMIN")

                                // SUPPRESSION PRODUIT ADMIN
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/products/**"
                                )
                                .hasRole("ADMIN")

                                .anyRequest()
                                .permitAll()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}

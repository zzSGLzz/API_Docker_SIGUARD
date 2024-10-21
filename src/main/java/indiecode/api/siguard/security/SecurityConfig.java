/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.security;

import indiecode.api.siguard.security.Jwt.JwtUtils;
import indiecode.api.siguard.security.Provider.EmailAuthProvider;
import indiecode.api.siguard.security.Provider.GuarderiaAuthProvider;
import indiecode.api.siguard.security.filter.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 *
 * @author zzsglzz
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private EmailAuthProvider emailAuthProvider;
    @Autowired
    private GuarderiaAuthProvider guarderiaAuthProvider;

    @Autowired
    private JwtUtils jwtUtils;


    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Habilitar CORS
                .authorizeHttpRequests(authz -> authz
                                //Configurar endpoints publicos
                                    //Login, Register, LogOut
                                .requestMatchers("/auth/**").permitAll()
                                //Configurar endpoints privados
                                .requestMatchers("/documento/**").permitAll()

                                .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("http://localhost:5173/")
                        .failureUrl("/login?error=true")// Redirige a la misma página con un parámetro de error si falla
                        .permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sin estado (JWT)
                //.httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
   @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(emailAuthProvider) // Register with own Email
                .authenticationProvider(guarderiaAuthProvider) //Register with Guarderia name
                .build();
    }

    /**
     * Metodo que solo permite peticiones desde el puerto 5173 del localhost a la API
     * @return CorsConfiguration
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/")); // Permitir tu frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(false); // Permitir enviar cookies o credenciales
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    
}

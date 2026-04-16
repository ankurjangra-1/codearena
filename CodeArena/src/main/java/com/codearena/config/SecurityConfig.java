package com.codearena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codearena.security.JwtFilter;

@Configuration
public class SecurityConfig {

	private final JwtFilter jwtFilter;
	public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
	
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            			    .requestMatchers("/users").permitAll()          
            			    .requestMatchers("/auth/**").permitAll()        
            			    .requestMatchers("/admin/**").hasAuthority("ADMIN")
            			    .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
            			    .requestMatchers("/api/problems").hasAuthority("ADMIN")
            			    .requestMatchers("/api/problems/**").authenticated()
            			    .requestMatchers("/api/submissions/**").authenticated()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
 
}
package com.bug_tracker.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return  http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers(HttpMethod.POST,"/user/register")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/user/users")
                            .permitAll()
                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults())
                    .formLogin(withDefaults())
                    .sessionManagement(Session-> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();

    }

    @Bean
    public AuthenticationProvider authentProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return authenticationProvider;
    }
}

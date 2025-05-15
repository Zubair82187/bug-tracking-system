package com.bug_tracker.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter){
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return  http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests((authorize) -> authorize

                            //Authorization for userController and AuthController
                            .requestMatchers(HttpMethod.POST,"/user/register", "/auth/login")//Anyone can register and login there is no need to authentication and authorization
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/user/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/user/delete/{id}").hasRole("ADMIN")

                            //Authorization for bug controller
                            .requestMatchers(HttpMethod.POST, "/bugs/create").hasRole("TESTER")       //only tester can post a bug
                            .requestMatchers(HttpMethod.GET, "/bugs/{id}", "/bugs/").permitAll()     //Anyone tester, developer or admin can get bugs
                            .requestMatchers(HttpMethod.PATCH, "/bugs/{id}/assign_bug_to_id/{assign_to_id}").hasAnyRole("ADMIN", "TESTER")      //Only tester and admin can assign a bug
                            .requestMatchers(HttpMethod.PATCH, "/bugs/update_bug_status/{id}").permitAll()       //Anyone can update bug status
                            .requestMatchers(HttpMethod.DELETE, "/bugs//delete/{id}").hasAnyRole("TESTER", "ADMIN")     // Tester and admin both can delete a bug

                            //Authorization for project controller

                            .requestMatchers(HttpMethod.GET, "/project/").hasRole("ADMIN")      //Only admin can access this
                            .requestMatchers(HttpMethod.POST, "/project/add").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/project//delete/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/project/{id}").permitAll()       //Anyone can access this

                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults())
                    .formLogin(AbstractHttpConfigurer::disable)
                    .sessionManagement(Session-> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationProvider authentProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

package com.example.homework27.Config;

import com.example.homework27.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/product/get").permitAll()
                .requestMatchers("/api/v1/product/get-id/{id}").permitAll()
                .requestMatchers("/api/v1/auth/add").permitAll()
                .requestMatchers("/api/v1/product/add").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/product/update/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/product/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/update/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/changestatus/{id}/{status}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/delete/{id}").hasAnyAuthority("CUSTOMER","ADMIN")
                .requestMatchers("/api/v1/order/add/{productid}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/get").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/**").hasAnyAuthority("CUSTOMER","ADMIN")
                .requestMatchers("/api/v1/auth/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/auth/deletebyadmin/{userid}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete").hasAuthority("CUSTOMER")
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

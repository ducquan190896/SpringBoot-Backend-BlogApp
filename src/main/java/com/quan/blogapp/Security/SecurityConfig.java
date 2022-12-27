package com.quan.blogapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.quan.blogapp.Security.Filter.FilterAuthentication;
import com.quan.blogapp.Security.Filter.FilterException;
import com.quan.blogapp.Security.Filter.FilterJwtAuthorization;
import com.quan.blogapp.Service.UsersService;
import com.quan.blogapp.Service.Implementation.UsersServiceIml;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    UsersServiceIml usersServiceIml;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        FilterAuthentication filterAuthentication = new FilterAuthentication(customAuthenticationManager);
        filterAuthentication.setFilterProcessesUrl("/login");

        http.csrf().disable()
        .authorizeRequests()
        // .antMatchers(HttpMethod.POST, "/**/register").permitAll()
        .anyRequest().permitAll()
        .and()
        // .authenticationProvider(authenticationProvider())
        // .addFilterBefore(new FilterException(), filterAuthentication.getClass())
        // .addFilter(filterAuthentication)
        // .addFilterAfter(new FilterJwtAuthorization(), filterAuthentication.getClass())
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        return http.build();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(usersServiceIml);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}

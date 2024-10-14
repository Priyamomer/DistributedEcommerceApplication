//package com.example.gateway.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//    @Bean
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception
//             {
//                 http
//                         .authorizeHttpRequests(authorize -> authorize
//                                 .anyRequest().permitAll()
//                         ).csrf().disable().cors().disable()
//                         .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//                 return http.build();
//    }
////    {
////        http .authorizeHttpRequests((authorize)-> authorize.requestMatchers("/products"). permitAll()
////                .requestMatchers("/search").permitAll()
////                .anyRequest().authenticated());
////                ;
////        return http.build();
////    }
////    @Bean
////    @Order(2)
////    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
////            throws Exception {
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .anyRequest().authenticated()
////                )
////                // Form login handles the redirect to the login page from the
////                // authorization server filter chain
////                .formLogin(Customizer.withDefaults());
////
////        return http.build();
////    }
//
//}
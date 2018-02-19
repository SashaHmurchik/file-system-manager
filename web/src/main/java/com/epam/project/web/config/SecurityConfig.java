//package com.epam.project.web.config;
//
//import com.epam.project.service.security.PersonAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private PersonAuthenticationProvider personAuthenticationProvider;
//
//    @Autowired
//    private AuthenticationEntryPoint authEntryPoint;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(personAuthenticationProvider);
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user").password("password").roles("USER").and()
////                .withUser("admin").password("password").roles("USER", "ADMIN");
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .authenticationEntryPoint(authEntryPoint);
//
//    }
//}

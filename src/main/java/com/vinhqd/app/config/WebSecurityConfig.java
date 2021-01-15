package com.vinhqd.app.config;

import com.vinhqd.app.component.CustomSuccessHandler;
import com.vinhqd.app.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/**").permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/login?accessDenied");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_login")
                .loginPage("/login")
                .successHandler(successHandler)
                .failureUrl("/login?incorrectAccount")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?logout");
        http.sessionManagement()
                .invalidSessionUrl("/login?invalidSession")
                .maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

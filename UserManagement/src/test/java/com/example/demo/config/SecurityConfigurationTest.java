package com.example.demo.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Filter.JWTFilter;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfiguration.class, HttpSecurity.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigurationTest {
    @MockBean
    private JWTFilter jWTFilter;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @MockBean
    private SecurityFilterChain securityFilterChain;

    @Test
    @Disabled("TODO: Complete this test")
    void testSecurityFilterChain() throws Exception {

        AuthenticationManagerBuilder authenticationBuilder = new AuthenticationManagerBuilder(null);
        securityConfiguration.securityFilterChain(new HttpSecurity(null, authenticationBuilder, new HashMap<>()));
    }


    @Test
    void testGetPasswordEncoder() {
        assertTrue(securityConfiguration.getPasswordEncoder() instanceof NoOpPasswordEncoder);
    }


    @Test
    void testAuthenticationManager() throws Exception {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        securityConfiguration.authenticationManager(new AuthenticationConfiguration());
    }
}


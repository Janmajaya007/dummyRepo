package com.example.demo.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Service.JwtService;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {JWTFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JWTFilterTest {
    @Autowired
    private JWTFilter jWTFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isAsyncStarted());
        assertTrue(request.isActive());
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getServletPath());
        assertEquals(80, request.getServerPort());
        assertEquals("localhost", request.getServerName());
        assertEquals("http", request.getScheme());
        assertEquals("", request.getRequestURI());
        assertEquals(80, request.getRemotePort());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("", request.getMethod());
        assertEquals(80, request.getLocalPort());
        assertEquals("localhost", request.getLocalName());
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertEquals("", request.getContextPath());
        assertEquals(-1L, request.getContentLengthLong());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal2() throws IOException, ServletException {

        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(null, response, filterChain);
    }


    @Test
    void testDoFilterInternal3() throws IOException, ServletException {
        DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }


    @Test
    void testDoFilterInternal4() throws IOException, ServletException, UsernameNotFoundException {
        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
        verify(jwtService).validateToken(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(userDetailsService).loadUserByUsername(Mockito.<String>any());
        verify(request).getRemoteAddr();
        verify(request).getHeader(Mockito.<String>any());
        verify(request).getSession(anyBoolean());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }


    @Test
    void testDoFilterInternal5() throws IOException, ServletException, UsernameNotFoundException {
        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
        verify(jwtService).validateToken(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(userDetailsService).loadUserByUsername(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal6() throws IOException, ServletException, UsernameNotFoundException {

        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(null);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal7() throws IOException, ServletException, UsernameNotFoundException {

        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(null);
        DefaultMultipartHttpServletRequest request = mock(DefaultMultipartHttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
    }
}


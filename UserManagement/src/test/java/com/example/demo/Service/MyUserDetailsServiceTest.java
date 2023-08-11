package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MyUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class MyUserDetailsServiceTest {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }


    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("no user found with that username");
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(userList);
        UserDetails actualLoadUserByUsernameResult = myUserDetailsService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(userRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("no user found with that username"));
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername4() throws UsernameNotFoundException {

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPetName(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("no user found with that username");
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(userList);
        myUserDetailsService.loadUserByUsername("janedoe");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername5() throws UsernameNotFoundException {

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPetName(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("no user found with that username");
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(userList);
        myUserDetailsService.loadUserByUsername("janedoe");
    }
}


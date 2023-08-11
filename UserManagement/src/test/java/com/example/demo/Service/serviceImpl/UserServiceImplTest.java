package com.example.demo.Service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testAddUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userRepository.saveAndFlush(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setPetName("Bella");
        user2.setRole("Role");
        user2.setUsername("janedoe");
        assertSame(user, userServiceImpl.addUser(user2));
        verify(userRepository).saveAndFlush(Mockito.<User>any());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertTrue(userServiceImpl.loginUser("janedoe", "iloveyou"));
        verify(userRepository).validateUser(Mockito.<String>any(), Mockito.<String>any());
    }


    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(userServiceImpl.getAllUsers());
        verify(userRepository).findAll();
    }


    @Test
    void testGetAllUsers2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userServiceImpl.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertEquals(1, actualAllUsers.size());
        verify(userRepository).findAll();
    }


    @Test
    void testPetNameExist() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userRepository.findByPetName(Mockito.<String>any())).thenReturn(user);
        assertTrue(userServiceImpl.petNameExist("Bella"));
        verify(userRepository).findByPetName(Mockito.<String>any());
    }


    @Test
    void testForgetPassword() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setPetName("Bella");
        user2.setRole("Role");
        user2.setUsername("janedoe");
        when(userRepository.saveAndFlush(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findBypetNameAnduserName(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertTrue(userServiceImpl.forgetPassword("Bella", "janedoe", "iloveyou"));
        verify(userRepository).findBypetNameAnduserName(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).saveAndFlush(Mockito.<User>any());
    }

    @Test
    void testGetUserRole() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userRepository.findByUsernameAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertEquals("Role", userServiceImpl.getUserRole("janedoe", "iloveyou"));
        verify(userRepository).findByUsernameAndPassword(Mockito.<String>any(), Mockito.<String>any());
    }


    @Test
    void testGetUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userRepository.findByUsernameAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertSame(user, userServiceImpl.getUser("janedoe", "iloveyou"));
        verify(userRepository).findByUsernameAndPassword(Mockito.<String>any(), Mockito.<String>any());
    }
}


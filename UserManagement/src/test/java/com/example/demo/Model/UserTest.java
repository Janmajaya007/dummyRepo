package com.example.demo.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private  User user;

    @BeforeEach
    public void init(){
        user= new User();
    }
    @Test
    void testCanEqual() {
        assertFalse((new User()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
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
        assertTrue(user.canEqual(user2));
    }

    @Test
    void testUserInputs(){
         int id=1;
         String username="user";
         String password="pass";
         String email="email";
         String petName="pet";
         String role="role";

         user.setId(id);
         user.setUsername(username);
         user.setPassword(password);
         user.setEmail(email);
         user.setPetName(petName);
         user.setRole(role);

        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals(user.getPassword(), password);
        Assertions.assertEquals(user.getEmail(), email);
        Assertions.assertEquals(user.getPetName(), petName);
        Assertions.assertEquals(user.getRole(), role);
    }

    @Test
    void allArgsConstructorTest(){
        int id=1;
        String username="user";
        String password="pass";
        String email="email";
        String petName="pet";
        String role="role";

        User user1= new User(id, username,password, email, petName, role);

        Assertions.assertEquals(user1.getId(), id);
        Assertions.assertEquals(user1.getUsername(), username);
        Assertions.assertEquals(user1.getPassword(), password);
        Assertions.assertEquals(user1.getEmail(), email);
        Assertions.assertEquals(user1.getPetName(), petName);
        Assertions.assertEquals(user1.getRole(), role);
    }
}


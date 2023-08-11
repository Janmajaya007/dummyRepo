package com.example.demo.Controller;

import static org.mockito.Mockito.when;

import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;


    @Test
    void testForgetPassword() throws Exception {
        when(userService.forgetPassword(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/v1/forgotPassword")
                .param("newPassword", "foo")
                .param("petName", "foo")
                .param("username", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }


    @Test
    void testForgetPassword2() throws Exception {
        when(userService.forgetPassword(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/v1/forgotPassword")
                .param("newPassword", "foo")
                .param("petName", "foo")
                .param("username", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }


    @Test
    void testGetRole() throws Exception {
        when(userService.getUserRole(Mockito.<String>any(), Mockito.<String>any())).thenReturn("User Role");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/v1/getRole")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User Role"));
    }


    @Test
    void testGetRole2() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userService.getUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/v1/getUser")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"username\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"petName\":\"Bella\","
                                        + "\"role\":\"Role\"}"));
    }


    @Test
    void testIsPetNameExist() throws Exception {
        when(userService.petNameExist(Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/v1/IsPetNameExist/{petName}",
                "Bella");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }


    @Test
    void testIsPetNameExist2() throws Exception {
        when(userService.petNameExist(Mockito.<String>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/v1/IsPetNameExist/{petName}",
                "Bella");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    @Test
    void testIsPetNameExist3() throws Exception {
        when(userService.petNameExist(Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/v1/IsPetNameExist/{petName}",
                "Bella");
        requestBuilder.characterEncoding("Encoding");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }


    @Test
    void testLogiUser() throws Exception {
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Message\":\"User not logged in\",\"Token:\":null}"));
    }


    @Test
    void testLogiUser2() throws Exception {
        when(jwtService.generateToken(Mockito.<String>any(), Mockito.<String>any())).thenReturn("ABC123");
        when(userService.getUserRole(Mockito.<String>any(), Mockito.<String>any())).thenReturn("User Role");
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new RunAsUserToken("?", "Principal", "Credentials", authorities, Authentication.class));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"Message\":\"User successfully logged in\",\"Token:\":\"ABC123\"}"));
    }


    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setPetName("Bella");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userService.addUser(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setPetName("Bella");
        user2.setRole("Role");
        user2.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"username\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"petName\":\"Bella\","
                                        + "\"role\":\"Role\"}"));
    }
}


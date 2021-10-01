package com.osanda.example.controller;

import com.osanda.example.dto.UserDto;
import com.osanda.example.entity.User;
import com.osanda.example.helpers.UserHelper;
import com.osanda.example.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Test
    public void getAllUsersTest() throws Exception {

        User user3 = UserHelper.user3;
        List<UserDto> userList = List.of(new UserDto(user3));

        Mockito.when(this.userService.getAllUsersFromDatabase()).thenReturn(userList);

        this.mockMvc.perform(get(basePath + "/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Osanda")))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Wedamulla")));
    }


}

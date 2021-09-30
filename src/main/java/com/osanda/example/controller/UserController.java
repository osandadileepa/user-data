package com.osanda.example.controller;

import com.osanda.example.dto.UserDto;
import com.osanda.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${spring.data.rest.base-path}/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = this.userService.getAllUsersFromDatabase();

        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @SneakyThrows
    @PostMapping(value = "")
    private ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {

        UserDto savedUser = this.userService.saveUserDetails(userDto);

        return ResponseEntity.ok(savedUser);
    }


}

package com.osanda.example.controller;

import com.osanda.example.dto.UserDto;
import com.osanda.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * All the user related REST apis are defined here
 *
 * @author Osanda Wedamulla
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${spring.data.rest.base-path}/user")
public class UserController {

    private final UserService userService;

    /**
     * Get all the available users from database
     *
     * @return ResponseEntity<List<UserDto>>
     */
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = this.userService.getAllUsersFromDatabase();

        return ResponseEntity.ok(users);
    }

    /**
     * Create a new user with given details
     *
     * @return ResponseEntity<UserDto>
     */
    @SneakyThrows
    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {

        UserDto savedUser = this.userService.saveUserDetails(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Updated all the user details are give details with user ID
     *
     * @return ResponseEntity<UserDto>
     */
    @SneakyThrows
    @PatchMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {

        UserDto updatedUser = this.userService.updateUserDetails(userId, userDto);

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Get user from user id
     *
     * @return ResponseEntity<UserDto>
     */
    @SneakyThrows
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserFromId(@PathVariable("id") Long userId) {

        UserDto updatedUser = this.userService.getUserById(userId);

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete user from ID
     */
    @SneakyThrows
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        this.userService.deleteUserById(userId);
    }
}

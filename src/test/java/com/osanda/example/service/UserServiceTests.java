package com.osanda.example.service;

import com.osanda.example.entity.User;
import com.osanda.example.helpers.UserHelper;
import com.osanda.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllUsersTest() {
        User user1 = UserHelper.user1;
        User user2 = UserHelper.user2;
        User user3 = UserHelper.user3;

        List<User> myUserList = List.of(user1, user2, user3);

        when(this.userRepository.findAllByIdNotNull()).thenReturn(myUserList);

        var allUserList = this.userService.getAllUsersFromDatabase();

        assertEquals(3, allUserList.size());

        verify(this.userRepository, times(1)).findAllByIdNotNull();
    }
}

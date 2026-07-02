package com.craigreesedev.demo.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserControllerTest {

    @Test
    void updateUserViaFormDataReturnsUpdatedUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserController userController = new UserController(userRepository);

        User existingUser = new User(1L, "oldName", "Old Full Name");
        User updatedUser = new User(1L, "newName", "New Full Name");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userController.updateUser(1L, "newName", "New Full Name");

        assertEquals(updatedUser, result);
        assertEquals("newName", existingUser.getUserName());
        assertEquals("New Full Name", existingUser.getFullName());
        verify(userRepository).save(any(User.class));
    }
}

package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.apifilmeseries.entity.User;
import com.apifilmeseries.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
      
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        when(userRepository.findByusername(anyString())).thenReturn(mockUser);

     
        UserDetails userDetails = userService.loadUserByUsername("testUser");

        // Assert
        assertEquals(mockUser.getUsername(), userDetails.getUsername());
        assertEquals(mockUser.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {

        when(userRepository.findByusername(anyString())).thenReturn(null);


        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("unknownUser");
        });
    }
}

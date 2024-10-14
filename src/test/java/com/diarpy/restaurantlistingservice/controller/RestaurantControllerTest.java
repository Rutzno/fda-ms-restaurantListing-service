package com.diarpy.restaurantlistingservice.controller;

import com.diarpy.restaurantlistingservice.dto.RestaurantDto;
import com.diarpy.restaurantlistingservice.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Mack_TB
 * @since 14/10/2024
 * @version 1.0.0
 */

class RestaurantControllerTest {
    @InjectMocks
    private RestaurantController restaurantController;
    @Mock
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // In order for Mock and InjectMocks annotation to take effect
    }

    @Test
    public void testGetAllRestaurants() {
        // Mock the service behavior
        List<RestaurantDto> mockRestaurants = Arrays.asList(
                new RestaurantDto(1, "Restaurant 1", "address 1", "city 1", "description 1"),
                new RestaurantDto(2, "Restaurant 2", "address 2", "city 2", "description 2"),
                new RestaurantDto(3, "Restaurant 3", "address 3", "city 3", "description 3")
        );
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);

        // Call the controller method
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getAllRestaurants();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());
        // Verify that the service method was called
        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    public void testGetRestaurantById() {
        // Mock the service behavior
        Integer restaurantId = 1;
        RestaurantDto mockRestaurant = new RestaurantDto(1, "Restaurant 1", "address 1", "city 1", "description 1");
        when(restaurantService.findRestaurantById(restaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));
        // Call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.getRestaurantById(restaurantId);
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
        verify(restaurantService, times(1)).findRestaurantById(restaurantId);
    }

    @Test
    public void testSaveRestaurant() {
        // Mock the service behavior
        RestaurantDto mockRestaurant = new RestaurantDto(1, "Restaurant 1", "address 1", "city 1", "description 1");
        when(restaurantService.addRestaurant(mockRestaurant)).thenReturn(mockRestaurant);
        // Call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.saveRestaurant(mockRestaurant);
        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
        verify(restaurantService, times(1)).addRestaurant(mockRestaurant);
    }
}
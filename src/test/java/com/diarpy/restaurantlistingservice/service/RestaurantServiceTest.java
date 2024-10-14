package com.diarpy.restaurantlistingservice.service;

import com.diarpy.restaurantlistingservice.dto.RestaurantDto;
import com.diarpy.restaurantlistingservice.entity.Restaurant;
import com.diarpy.restaurantlistingservice.mapper.RestaurantMapper;
import com.diarpy.restaurantlistingservice.repository.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author Mack_TB
 * @since 14/10/2024
 * @version 1.0.0
 */

class RestaurantServiceTest {
    @InjectMocks
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepo restaurantRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
        // Mock the repository behavior
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Description 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Description 2")
                );
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);
        // Call the service method
        List<RestaurantDto> response = restaurantService.findAllRestaurants();
        // Verify the response
        assertEquals(mockRestaurants.size(), response.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            Restaurant restaurant = mockRestaurants.get(i);
            RestaurantDto expectedRestaurantDto = RestaurantMapper.INSTANCE.toRestaurantDto(restaurant);
            assertEquals(expectedRestaurantDto, response.get(i));
        }
        verify(restaurantRepo, times(1)).findAll();
    }

    @Test
    public void testAddRestaurant() {
        // Mock the repository behavior
        RestaurantDto mockRestaurantDTO = new RestaurantDto(1, "Restaurant 1", "address 1", "city 1", "description 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.toRestaurant(mockRestaurantDTO);
        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);
        // Call the service method
        RestaurantDto response = restaurantService.addRestaurant(mockRestaurantDTO);
        // Verify the response
        assertEquals(mockRestaurantDTO, response);
        verify(restaurantRepo, times(1)).save(mockRestaurant);
    }

    @Test
    public void testFindRestaurantById_ExistingID() {
        // Mock the repository behavior
        Integer restaurantId = 1;
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "address 1", "city 1", "description 1");
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(mockRestaurant));
        // Call the service method
        ResponseEntity<RestaurantDto> response = restaurantService.findRestaurantById(restaurantId);
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantId, response.getBody().getId());
        verify(restaurantRepo, times(1)).findById(restaurantId);
    }

    @Test
    public void testFindRestaurantById_NoExistingID() {
        // Mock the repository behavior
        Integer restaurantId = 1;
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());
        // Call the service method
        ResponseEntity<RestaurantDto> response = restaurantService.findRestaurantById(restaurantId);
        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(restaurantRepo, times(1)).findById(restaurantId);
    }
}
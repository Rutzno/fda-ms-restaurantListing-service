package com.diarpy.restaurantlistingservice.service;

import com.diarpy.restaurantlistingservice.dto.RestaurantDto;
import com.diarpy.restaurantlistingservice.entity.Restaurant;
import com.diarpy.restaurantlistingservice.mapper.RestaurantMapper;
import com.diarpy.restaurantlistingservice.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mack_TB
 * @since 28/09/2024
 * @version 1.0.0
 */

@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantDto> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                        .map(restaurant -> RestaurantMapper.INSTANCE.toRestaurantDto(restaurant))
                        .toList();
    }

    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant addedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantDto));
        return RestaurantMapper.INSTANCE.toRestaurantDto(addedRestaurant);
    }

    public ResponseEntity<RestaurantDto> findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if (restaurant.isPresent())
            return ResponseEntity.ok(RestaurantMapper.INSTANCE.toRestaurantDto(restaurant.get()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

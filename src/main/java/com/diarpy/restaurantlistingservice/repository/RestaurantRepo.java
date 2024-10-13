package com.diarpy.restaurantlistingservice.repository;

import com.diarpy.restaurantlistingservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mack_TB
 * @since 28/09/2024
 * @version 1.0.0
 */

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
}

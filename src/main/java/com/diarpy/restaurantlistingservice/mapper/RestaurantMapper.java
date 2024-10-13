package com.diarpy.restaurantlistingservice.mapper;

import com.diarpy.restaurantlistingservice.dto.RestaurantDto;
import com.diarpy.restaurantlistingservice.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Mack_TB
 * @since 28/09/2024
 * @version 1.0.0
 */

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDto toRestaurantDto(Restaurant restaurant);
    Restaurant toRestaurant(RestaurantDto restaurantDto);
}

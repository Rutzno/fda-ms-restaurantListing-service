package com.diarpy.restaurantlistingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 28/09/2024
 * @version 1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String description;
}

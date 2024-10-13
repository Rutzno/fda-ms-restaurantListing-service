package com.diarpy.restaurantlistingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

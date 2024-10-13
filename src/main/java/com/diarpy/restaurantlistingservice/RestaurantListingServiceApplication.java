package com.diarpy.restaurantlistingservice;

import com.diarpy.restaurantlistingservice.entity.Restaurant;
import com.diarpy.restaurantlistingservice.repository.RestaurantRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mack_TB
 * @since 28/09/2024
 * @version 1.0.0
 */

@SpringBootApplication
public class RestaurantListingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantListingServiceApplication.class, args);
    }

    /*@Bean
    CommandLineRunner loadData(RestaurantRepo restaurantRepository) {
        return args -> {
            // Create 8 restaurant objects
            Restaurant restaurant1 = new Restaurant(null, "Le Gourmet", "123 Street A", "Dakar", "Fine dining restaurant");
            Restaurant restaurant2 = new Restaurant(null, "Chez Fatou", "456 Street B", "Abidjan", "Traditional Ivorian cuisine");
            Restaurant restaurant3 = new Restaurant(null, "L'Africain", "789 Street C", "Bamako", "Modern African cuisine");
            Restaurant restaurant4 = new Restaurant(null, "Pizzeria Napoli", "101 Street D", "Lomé", "Italian Pizza specialists");
            Restaurant restaurant5 = new Restaurant(null, "Le Bistrot", "202 Street E", "Cotonou", "Casual dining with flair");
            Restaurant restaurant6 = new Restaurant(null, "Sushi Zen", "303 Street F", "Accra", "Authentic Japanese sushi");
            Restaurant restaurant7 = new Restaurant(null, "La Terrasse", "404 Street G", "Ouagadougou", "Outdoor cafe");
            Restaurant restaurant8 = new Restaurant(null, "La Maison du Poulet", "505 Street H", "Niamey", "Grilled chicken specialists");

            // Save restaurants in the repository
            List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5, restaurant6, restaurant7, restaurant8);
            restaurantRepository.saveAll(restaurants);
        };
    }*/
}

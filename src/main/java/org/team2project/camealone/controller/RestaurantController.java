package org.team2project.camealone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team2project.camealone.entity.RestaurantFavorites;
import org.team2project.camealone.entity.RestaurantList;
import org.team2project.camealone.service.RestaurantService;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/saveRestaurantToList")
    public ResponseEntity<String> saveRestaurantToList(@RequestBody RestaurantList restaurant) {
        restaurantService.saveRestaurantToList(restaurant);
        return ResponseEntity.ok("Restaurant saved to list successfully");
    }

    @PostMapping("/saveRestaurantToFavorites")
    public ResponseEntity<String> saveRestaurantToFavorites(@RequestBody RestaurantFavorites restaurant) {
        restaurantService.saveRestaurantToFavorites(restaurant);
        return ResponseEntity.ok("Restaurant saved to favorites successfully");
    }
}
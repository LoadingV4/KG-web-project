package org.team2project.camealone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team2project.camealone.entity.RestaurantFavorites;
import org.team2project.camealone.service.RestaurantFavoritesService;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class RestaurantFavoritesController {

    @Autowired
    private RestaurantFavoritesService service;

    @GetMapping
    public List<RestaurantFavorites> getAllFavorites() {
        return service.getAllFavorites();
    }

    @GetMapping("/{id}")
    public RestaurantFavorites getFavoriteById(@PathVariable Long id) {
        return service.getFavoriteById(id);
    }

    @GetMapping("/formatted/{id}")
    public String getFormattedFavoriteDetails(@PathVariable Long id) {
        return service.getFormattedFavoriteDetails(id);
    }
}
package org.team2project.camealone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team2project.camealone.entity.RestaurantFavorites;
import org.team2project.camealone.entity.RestaurantList;
import org.team2project.camealone.repository.RestaurantFavoritesRepository;
import org.team2project.camealone.repository.RestaurantListRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantListRepository restaurantListRepository;

    @Autowired
    private RestaurantFavoritesRepository restaurantFavoritesRepository;

    public void saveRestaurantToList(RestaurantList restaurant) {
        restaurantListRepository.save(restaurant);
    }

    public void saveRestaurantToFavorites(RestaurantFavorites restaurant) {
        restaurantFavoritesRepository.save(restaurant);
    }
}
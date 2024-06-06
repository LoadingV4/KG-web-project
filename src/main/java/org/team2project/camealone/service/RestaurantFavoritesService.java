package org.team2project.camealone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team2project.camealone.entity.RestaurantFavorites;
import org.team2project.camealone.repository.RestaurantFavoritesRepository;


import java.util.List;

@Service
public class RestaurantFavoritesService {
    @Autowired
    private RestaurantFavoritesRepository repository;

    public List<RestaurantFavorites> getAllFavorites() {
        return repository.findAll();
    }

    public RestaurantFavorites getFavoriteById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // 데이터를 가공하는 예시
    public String getFormattedFavoriteDetails(Long id) {
        RestaurantFavorites favorite = getFavoriteById(id);
        if (favorite != null) {
            return String.format("Name: %s, Rating: %.1f, Phone: %s, Photo: %s",
                    favorite.getName(),
                    favorite.getRating(),
                    favorite.getFormattedPhoneNumber(),
                    favorite.getPhotoUrl());
        }
        return "Favorite not found";
    }
}

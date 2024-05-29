package org.team2project.camealone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team2project.camealone.entity.RestaurantFavorites;

@Repository
public interface RestaurantFavoritesRepository extends JpaRepository<RestaurantFavorites, Long> {
}

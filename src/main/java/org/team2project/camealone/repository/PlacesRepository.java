package org.team2project.camealone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team2project.camealone.entity.Places;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Long> {
}

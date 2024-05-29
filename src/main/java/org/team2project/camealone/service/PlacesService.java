package org.team2project.camealone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team2project.camealone.entity.Places;
import org.team2project.camealone.repository.PlacesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlacesService {

    @Autowired
    private PlacesRepository placesRepository;

    public List<Places> getAllPlaces() {
        return placesRepository.findAll();
    }

    public Optional<Places> getPlaceById(Long number) {
        return placesRepository.findById(number);
    }

    public Places savePlace(Places places) {
        return placesRepository.save(places);
    }

    public void deletePlaceById(Long number) {
        placesRepository.deleteById(number);
    }
}

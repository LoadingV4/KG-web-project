package org.team2project.camealone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.team2project.camealone.entity.Places;
import org.team2project.camealone.service.PlacesService;

import java.util.List;

@Controller
@RequestMapping("/places")
public class PlacesController {
    // 샘플 코드 사용법 ---------------------------------------------------------------------------

    // mariaDB에서 springbootdb 데이터베이스 생성
    // port:3306으로 접속하는 이름 : springuser, 비밀번호 : password 인 유저 생성 후
        // CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'password';
        // GRANT ALL PRIVILEGES ON springbootdb.* TO springuser'@'localhost';
        // FLUSH PRIVILEGES;
    // localhost/places/add 로 정보 추가하는 샘플 코드 입력
    // localhost/places 에서 리스트 확인 가능

    // 샘플 코드 사용법 ---------------------------------------------------------------------------

    @Autowired
    private PlacesService placesService;

    @GetMapping
    public String getAllPlaces(Model model) {
        List<Places> places = placesService.getAllPlaces();
        model.addAttribute("places", places);
        return "placesList";
    }

    @GetMapping("/add")
    public String showAddPlaceForm(Model model) {
        model.addAttribute("place", new Places());
        return "addPlace";
    }

    @PostMapping
    public String createPlace(@ModelAttribute Places places) {
        placesService.savePlace(places);
        return "redirect:/places";
    }

    @GetMapping("/{id}")
    public String getPlaceById(@PathVariable("id") Long id, Model model) {
        Places places = placesService.getPlaceById(id).orElse(null);
        model.addAttribute("places", places);
        return "placeDetail";
    }

    @DeleteMapping("/{id}")
    public String deletePlaceById(@PathVariable("id") Long id) {
        placesService.deletePlaceById(id);
        return "redirect:/places";
    }
    @GetMapping("/places")
    public String getPlaces(Model model) {
        // model에 필요한 데이터를 추가
        return "placesList"; // 뷰 이름이 템플릿 파일 이름과 일치해야 합니다.
    }

}

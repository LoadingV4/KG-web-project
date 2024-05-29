package org.team2project.camealone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "places") // 장소 테이블
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private Long number;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "reviews", nullable = false)
    private Integer reviews;

    @Column(name = "contact", nullable = false, length = 255)
    private String contact;

    // 기본 생성자
    public Places() {
    }

    // 생성자
    public Places(String name, Double rating, Integer reviews, String contact) {
        this.name = name;
        this.rating = rating;
        this.reviews = reviews;
        this.contact = contact;
    }

    // Getter 및 Setter 메서드
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

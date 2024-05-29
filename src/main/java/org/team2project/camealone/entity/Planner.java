package org.team2project.camealone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "planner") // 여행 계획 테이블
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private Long number;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "contact", nullable = false, length = 255)
    private String contact;

    @Column(name = "create_date", nullable = false, length = 255)
    private String create_date;

    @Column(name = "planner_name", nullable = false, unique = true, length = 255)
    private String planner_name;

    // 기본 생성자
    public Planner() {
    }

    // 생성자
    public Planner(String name, Double rating, String contact, String create_date, String planner_name) {
        this.name = name;
        this.rating = rating;
        this.contact = contact;
        this.create_date = create_date;
        this.planner_name = planner_name;
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

    public Double getRating() {return rating;}

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getplanner_name() {
        return planner_name;
    }

    public void setplanner_name(String planner_name) {this.planner_name = planner_name; }

}

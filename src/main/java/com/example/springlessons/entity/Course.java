package com.example.springlessons.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Course {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @NaturalId
    @Column(length = 50, nullable = false)
    private String title; // название курса

    @Getter
    @Setter
    private LocalDate start; // начало курса

    @Getter
    @Setter
    private int price; // стоимость курса

    @Getter
    @Setter
    private double duration; // продолжительность курса
}

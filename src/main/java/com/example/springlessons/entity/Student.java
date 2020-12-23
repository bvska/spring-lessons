package com.example.springlessons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Student {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
}

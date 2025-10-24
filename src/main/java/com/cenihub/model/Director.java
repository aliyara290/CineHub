package com.cenihub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String nationality;

    @Column(name = "birth_year")
    private Integer birthYear;

//    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<Film> films = new ArrayList<>();
}
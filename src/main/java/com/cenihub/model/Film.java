package com.cenihub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "id")
    private String id;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "release_year", nullable = false)
    private LocalDate releaseYear;

    @Column (name = "duration", nullable = false)
    private Integer duration;

    @Column (name = "synopsis", nullable = false)
    private String synopsis;

    @Column (name = "rating")
    private Double rating;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Director director;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;
}

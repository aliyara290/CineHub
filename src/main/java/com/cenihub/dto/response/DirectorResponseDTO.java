package com.cenihub.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorResponseDTO {
    private Long id;
    private String name;
    private String nationality;
    private Integer birthYear;
    private Integer filmCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
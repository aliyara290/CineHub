package com.cenihub.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Integer filmCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
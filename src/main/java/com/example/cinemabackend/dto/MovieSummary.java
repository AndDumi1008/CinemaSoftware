package com.example.cinemabackend.dto;

import com.example.cinemabackend.entity.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieSummary {

    private String id;

    private String title;
    private String imageUrl;
    private String trailerUrl;
    private Category category;
}

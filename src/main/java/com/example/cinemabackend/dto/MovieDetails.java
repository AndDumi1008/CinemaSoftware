package com.example.cinemabackend.dto;

import com.example.cinemabackend.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieDetails {

    private String id;

    private String title;
    private String imageUrl;
    private String trailerUrl;
    private String category;
    private List<String> projectionDate;
}

package com.example.cinemabackend.service;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;

import java.util.List;

public interface MovieService {

    MovieDetails save(MovieDetails movieEntity);

    List<MovieSummary> findAll();

    MovieDetails update(MovieDetails movieDetails, String id);

    void deleteById(String id);

    MovieDetails getMovieById(String id);
}

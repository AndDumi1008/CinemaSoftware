package com.example.cinemabackend.service;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;
import com.example.cinemabackend.entity.Category;
import com.example.cinemabackend.entity.MovieEntity;
import com.example.cinemabackend.mapper.MovieMapper;
import com.example.cinemabackend.repo.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    public MovieDetails save(MovieDetails movieDetails) {
        movieRepository.save(movieMapper.movieDetailsToMovieEntity(movieDetails));
        return movieDetails;
    }


    @Override
    public List<MovieSummary> findAll() {
        return movieMapper.movieItemListToMovieSummaryList(movieRepository.findAll());
    }


    @Override
    public MovieDetails update(MovieDetails movieDetails, String id) {
        try {
            ObjectId movieObjectId = movieMapper.StringToObjectId(id);
            MovieEntity movieEntity = movieRepository.findById(movieObjectId).orElseThrow();

            movieEntity.setTitle(movieDetails.getTitle());
            movieEntity.setCategory(Category.valueOf(movieDetails.getCategory()));
            movieEntity.setImageUrl(movieDetails.getImageUrl());
            movieEntity.setTrailerUrl(movieDetails.getTrailerUrl());
            movieEntity.setProjectionDate(movieMapper.stringToLdtList(movieDetails.getProjectionDate()));
//            movieEntity.setReservedSeats(movieMapper.stringToInteger(movieDetails.getReservedSeats()));
            movieEntity.setReservedSeats(movieMapper.mapOfDto(movieDetails.getReservedSeats()));

            MovieEntity updateMovie = movieRepository.save(movieEntity);
            return movieMapper.movieEntityToMovieDetails(updateMovie);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The event was not found.", e);
        }
    }


    @Override
    public void deleteById(String id) {
        try {
            ObjectId movieObjectId = movieMapper.StringToObjectId(id);
            movieRepository.findById(movieObjectId).orElseThrow();
            movieRepository.deleteById(movieObjectId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie was not found.", e);
        }
    }


    @Override
    public MovieDetails getMovieById(String id) {
        try {
            MovieEntity movie;
            ObjectId movieObjectId = movieMapper.StringToObjectId(id);
            movie = movieRepository.findById(movieObjectId).orElseThrow();
            return movieMapper.movieEntityToMovieDetails(movie);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie was not found.", e);
        }
    }

}

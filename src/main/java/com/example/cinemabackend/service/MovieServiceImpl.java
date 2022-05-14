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
import java.util.NoSuchElementException;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    /**
     * @param movieDetails
     * @return
     */
    @Override
    public MovieDetails save(MovieDetails movieDetails) {
        movieRepository.save(movieMapper.movieDetailsToMovieEntity(movieDetails));
        return movieDetails;
    }

    /**
     * @return
     */
    @Override
    public List<MovieSummary> findAll() {
        return movieMapper.movieItemListToMovieSummaryList(movieRepository.findAll());
    }

    /**
     * @param movieDetails
     * @param id
     * @return
     */
    @Override
    public MovieDetails update(MovieDetails movieDetails, String id) {
        try {
            ObjectId movieObjectId = movieMapper.StringToObjectId(id);
            MovieEntity movieEntity = movieRepository.findById(movieObjectId).orElseThrow();

            movieEntity.setTitle(movieDetails.getTitle());
            movieEntity.setCategory(Category.valueOf(movieDetails.getCategory()));
            movieEntity.setImageUrl(movieDetails.getImageUrl());
            movieEntity.setTrailerUrl(movieDetails.getTrailerUrl());
//            movieEntity.setProjectionDate(movieDetails.getProjectionDate);  //TODO: do this method

            MovieEntity updateMovie = movieRepository.save(movieEntity);
            return movieMapper.movieEntityToMovieDetails(updateMovie);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The event was not found.", e);
        }
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(String id) {
        try {
            ObjectId eventObjectId = movieMapper.StringToObjectId(id);
            movieRepository.findById(eventObjectId).orElseThrow();
            movieRepository.deleteById(eventObjectId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie was not found.", e);
        }
    }

    /**
     * @param id
     * @return
     */
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

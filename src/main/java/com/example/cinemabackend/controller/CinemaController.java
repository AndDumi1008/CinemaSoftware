package com.example.cinemabackend.controller;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;
import com.example.cinemabackend.dto.Checkout;
import com.example.cinemabackend.service.CheckoutService;
import com.example.cinemabackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CinemaController {

    private final MovieService movieService;
    private final CheckoutService checkoutService;

    @Autowired
    public CinemaController(MovieService movieService, CheckoutService checkoutService) {
        this.movieService = movieService;
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public List<MovieSummary> getAllMovies() { //Return the list of books
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDetails getMovieById(@PathVariable final String id) { //Return book by id
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDetails saveMovie(@RequestBody MovieDetails movieEntity) {
        return movieService.save(movieEntity);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDetails updateEvent(@RequestBody MovieDetails movieDetails, @PathVariable String id) {
        return movieService.update(movieDetails, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventById(@PathVariable String id) {
        movieService.deleteById(id);
    }

    @PutMapping("/buy={id}")
    public Checkout buyTicket(@RequestBody Checkout ticket, @PathVariable String id) {
        return checkoutService.update(ticket, id);
    }
}
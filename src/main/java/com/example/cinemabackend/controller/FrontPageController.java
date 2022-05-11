package com.example.cinemabackend.controller;

import com.example.cinemabackend.components.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cinema")
public class FrontPageController {

    public List<Movie> movieList = new ArrayList<>();
    Movie movieTemp;

    public List<String> listDate = new ArrayList<String>();



    @RequestMapping("/add")
    public void addMovie() {
        listDate.add(new String("20:20"));
        listDate.add(new String("13:2"));
        listDate.add(new String("21:45"));
        listDate.add(new String("9:30"));



        Movie movie1 = new Movie("Avangers 1", "Action", "Avengers1.png", "https://www.youtube.com/watch?v=eOrNdBpGMv8", listDate);
        Movie movie2 = new Movie("Avangers 2", "Action", "Avengers2.png", "https://www.youtube.com/watch?v=tmeOjFno6Do", listDate);
        Movie movie3 = new Movie("Batman", "Action", "Batman.png", "https://www.youtube.com/watch?v=mqqft2x_Aa4", listDate);
        Movie movie4 = new Movie("Central Intelligence", "Comedy/Action", "CentralIntelligence.png", "https://www.youtube.com/watch?v=MxEw3elSJ8M", listDate);

        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
    }


    @RequestMapping("/movies")
    public List<Movie> getMovies() { //Return the list of books
        movieList.sort(Comparator.naturalOrder());
        return movieList;
    }

    @RequestMapping("/movies/id={id}")
    public Movie getMovies(@PathVariable final String id) { //Return book by id
        return movieList.get(Integer.parseInt(id));

    }

    @GetMapping("/homepage")
    public String welcome() {
        return "Now you are in Cinema Homepage";
    }

}
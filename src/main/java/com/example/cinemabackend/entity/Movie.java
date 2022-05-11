package com.example.cinemabackend.entity;

import java.util.List;

public class Movie implements Comparable<Movie> {

    private final String title;
    private final String genre;
    private final String posterLocation;
    private final String movieTrailer;

    private final List<String> projectionDate;


    public Movie(String title, String directorName, String posterLocation, String movieTrailer, List<String> projectionDate) {
        this.title = title;
        this.genre = directorName;
        this.posterLocation = posterLocation;
        this.movieTrailer = movieTrailer;
        this.projectionDate = projectionDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", posterLocation='" + posterLocation + '\'' +
                ", projectionDate=" + projectionDate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getProjectionDate() {
        return projectionDate;
    }

    public String getPosterLocation() {
        return posterLocation;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    @Override
    public int compareTo(Movie o) {
        if(this.title.compareTo(o.title) < 0){
            return -1;
        }
        else if(this.title.compareTo(o.title) > 0){
            return 1;
        }
        else{   //when is the same author
            return Integer.compare(this.genre.compareTo(o.genre), 0);
        }
    }
}
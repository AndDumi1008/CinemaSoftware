package com.example.cinemabackend.service;

import com.example.cinemabackend.dto.Checkout;
import com.example.cinemabackend.entity.MovieEntity;
import com.example.cinemabackend.mapper.MovieMapper;
import com.example.cinemabackend.repo.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public CheckoutServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }


    @Override
    public Checkout update(Checkout checkout, String id) {
        try {
            ObjectId movieObjectId = movieMapper.StringToObjectId(id);
            MovieEntity movieEntity = movieRepository.findById(movieObjectId).orElseThrow();
//            if (!movieEntity.getProjectionDate().contains(checkout.getProjectionTime()))
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Date was not found.");

//            if(movieEntity.getReservedSeats().containsKey(checkout.getProjectionTime()) || movieEntity.getProjectionDate().contains(checkout.getProjectionTime())) {
//                Map<LocalDateTime, Set<Integer>> reservedSeats = movieEntity.getReservedSeats();
//
//                reservedSeats.put(checkout.getProjectionTime(), checkout.getReservedSeats());
//
//                movieEntity.setReservedSeats(reservedSeats);
//                movieRepository.save(movieEntity);
//
//                checkout.setName(movieEntity.getTitle());
//                return checkout;
//            }


            if (movieEntity.getReservedSeats().get(checkout.getProjectionTime()).containsAll(checkout.getReservedSeats()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat(s) already reserved.");


            Set<Integer> oldSeats = movieEntity.getReservedSeats().get(checkout.getProjectionTime());
            oldSeats.addAll(checkout.getReservedSeats());

//            Map<LocalDateTime, Set<Integer>> reservedSeats = movieEntity.getReservedSeats();
//            Map<LocalDateTime, Set<Integer>> reservedSeats = Map.of(checkout.getProjectionTime(), Set.of());

//            reservedSeats.put(checkout.getProjectionTime(), oldSeats);
            movieEntity.getReservedSeats().put(checkout.getProjectionTime(), oldSeats);
            movieEntity.setReservedSeats(movieEntity.getReservedSeats());
            movieRepository.save(movieEntity);

            checkout.setName(movieEntity.getTitle());
            return checkout;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie was not found.", e);
        }
    }
}

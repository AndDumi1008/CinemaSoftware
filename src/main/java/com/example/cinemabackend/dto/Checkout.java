package com.example.cinemabackend.dto;

import com.example.cinemabackend.entity.MovieEntity;
import com.example.cinemabackend.service.MovieService;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Checkout {

//    private String movieId;
    private String name;
    private Float totalPrice;
    private LocalDateTime projectionTime;

    @Positive
    private Set<Integer> reservedSeats;

}

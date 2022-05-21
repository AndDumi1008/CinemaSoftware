package com.example.cinemabackend.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Document("movieitems")
@Builder
@Data
public class MovieEntity {

    @Id
    private ObjectId id;


    private String title;
    private String imageUrl;
    private String trailerUrl;
    private Category category;
    private List<LocalDateTime> projectionDate;

    private Map<LocalDateTime, Set<Integer>> reservedSeats;

}
package com.example.cinemabackend.entity;

import com.example.cinemabackend.service.TicketImpl;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
public class User {

    private ObjectId movieId;
    private TicketImpl tickets;
}

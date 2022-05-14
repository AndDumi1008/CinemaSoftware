package com.example.cinemabackend.repo;

import com.example.cinemabackend.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<MovieEntity, ObjectId> {
}

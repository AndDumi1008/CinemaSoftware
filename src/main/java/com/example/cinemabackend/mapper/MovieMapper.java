package com.example.cinemabackend.mapper;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;
import com.example.cinemabackend.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class MovieMapper {

    @Named("objectIdToString")
    public String objectIdToString(ObjectId objectId) {
        return objectId.toString();
    }

    @Named("StringToObjectId")
    public ObjectId StringToObjectId(String stringValue) {

        if (stringValue == null || stringValue.length() == 0)
            return null;

        return new ObjectId(stringValue);
    }

    public abstract List<MovieSummary> movieItemListToMovieSummaryList(List<MovieEntity> movieEntityList);


    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    public abstract MovieDetails movieEntityToMovieDetails(MovieEntity movieEntity);

    @Mapping(source = "id", target = "id", qualifiedByName = "StringToObjectId")
    public abstract MovieEntity movieDetailsToMovieEntity(MovieDetails movieSummary);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    public abstract MovieSummary movieEntityToMovieSummary(MovieEntity movieEntity);

}

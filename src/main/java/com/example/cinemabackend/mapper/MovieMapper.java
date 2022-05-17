package com.example.cinemabackend.mapper;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;
import com.example.cinemabackend.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    // LocalDateTime to String method.
    @Named("ldtToString")
    public List<String> ldtToString(List<LocalDateTime> localDateTImeList) {
        if(localDateTImeList == null || localDateTImeList.isEmpty()) {
            return null;
        }
        List<String> stringDates = new ArrayList<>();
        for(LocalDateTime  date : localDateTImeList) {
            stringDates.add(date.toString());
        }
        return stringDates;
    }

    // LocalDateTime to String method.
    @Named("stringToLdt")
    public List<LocalDateTime> stringToLdt(List<String> stringDateList) {
        if(stringDateList == null || stringDateList.isEmpty()) {
            return null;
        }
        List<LocalDateTime> ldtDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        for(String date : stringDateList) {
            ldtDates.add(LocalDateTime.parse(date, formatter));
        }
        return ldtDates;
    }
    public abstract List<MovieSummary> movieItemListToMovieSummaryList(List<MovieEntity> movieEntityList);


    @Mapping(target = "id", qualifiedByName = "objectIdToString")
    @Mapping(target = "projectionDate", qualifiedByName = "ldtToString")
    public abstract MovieDetails movieEntityToMovieDetails(MovieEntity movieEntity);

    @Mapping(target = "id", qualifiedByName = "StringToObjectId")
    @Mapping(target = "projectionDate", qualifiedByName = "stringToLdt")
    public abstract MovieEntity movieDetailsToMovieEntity(MovieDetails movieSummary);

    @Mapping(target = "id", qualifiedByName = "objectIdToString")
    public abstract MovieSummary movieEntityToMovieSummary(MovieEntity movieEntity);

}

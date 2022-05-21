package com.example.cinemabackend.mapper;

import com.example.cinemabackend.dto.MovieDetails;
import com.example.cinemabackend.dto.MovieSummary;
import com.example.cinemabackend.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class MovieMapper {

    @Named("objectIdToString")
    public String objectIdToString(ObjectId objectId) {
        return objectId.toString();
    }

    @Named("StringToObjectId")
    public ObjectId StringToObjectId(String stringValue) {

        if (stringValue == null || stringValue.length() == 0) return null;

        return new ObjectId(stringValue);
    }

    // LocalDateTime to String method.
    @Named("ldtToStringList")
    public List<String> ldtToStringList(List<LocalDateTime> localDateTImeList) {
        if (localDateTImeList == null || localDateTImeList.isEmpty()) {
            return null;
        }
        List<String> stringDates = new ArrayList<>();
        for (LocalDateTime date : localDateTImeList) {
            stringDates.add(date.toString());
        }
        return stringDates;
    }

    // LocalDateTime to String method.
    @Named("stringToLdtList")
    public List<LocalDateTime> stringToLdtList(List<String> stringDateList) {
        if (stringDateList == null || stringDateList.isEmpty()) {
            return null;
        }
        List<LocalDateTime> ldtDates = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        for (String date : stringDateList) {
            ldtDates.add(LocalDateTime.parse(date));
        }
        return ldtDates;
    }

    public abstract List<MovieSummary> movieItemListToMovieSummaryList(List<MovieEntity> movieEntityList);

    @Named("ldtToString")
    public String ldtToString(LocalDateTime stringDateList) {
        if (stringDateList == null)
            return null;

        return stringDateList.toString();
    }

    @Named("stringToLdt")
    public LocalDateTime stringToLdt(String stringDateList) {
        if (stringDateList == null)
            return null;

//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(stringDateList);
    }

    public Set<Integer> stringToInteger(Set<String> stringReservedSeats) {
        if (stringReservedSeats == null || stringReservedSeats.isEmpty()) {
            return null;
        }
        Set<Integer> seatNumber = new HashSet<>();

        for (String seat : stringReservedSeats) {
            seatNumber.add(Integer.parseInt(seat));
        }
        return seatNumber;
    }

    public Set<String> integerToString(Set<Integer> integerReservedSeats) {
        if (integerReservedSeats == null || integerReservedSeats.isEmpty()) {
            return null;
        }
        Set<String> seatNumber = new HashSet<>();

        for (Integer seat : integerReservedSeats) {
            seatNumber.add(seat.toString());
        }
        return seatNumber;
    }


    @Named("mapOfEntity")
    public Map<String, Set<String>> mapOfEntity(Map<LocalDateTime, Set<Integer>> mapOfDto) {
        Map<String, Set<String>> reservedSeats = new HashMap<>();

        if (mapOfDto == null) {
            return reservedSeats;
        }


        for (LocalDateTime date : mapOfDto.keySet()) {
            Set<String> seatsSet = new HashSet<>();
            for (Integer seats : mapOfDto.get(date)) {
                seatsSet.add(String.valueOf(seats));
            }
            reservedSeats.put(date.toString(), seatsSet);
        }
        return reservedSeats;
    }

    @Named("mapOfDto")
    public Map<LocalDateTime, Set<Integer>> mapOfDto(Map<String, Set<String>> mapOfEntity) {
        Map<LocalDateTime, Set<Integer>> reservedSeats = new HashMap<>();

        if (mapOfEntity == null) {
            return reservedSeats;
        }

        for (String date : mapOfEntity.keySet()) {
            Set<Integer> seatsSet = new HashSet<>();

            for (String seats : mapOfEntity.get(date)) {
                seatsSet.add(Integer.parseInt(seats));
            }
            reservedSeats.put(stringToLdt(date), seatsSet);
        }
        return reservedSeats;
    }

    @Mapping(target = "id", qualifiedByName = "objectIdToString")
    @Mapping(target = "projectionDate", qualifiedByName = "ldtToStringList")
    @Mapping(target = "reservedSeats", qualifiedByName = "mapOfEntity")
    public abstract MovieDetails movieEntityToMovieDetails(MovieEntity movieEntity);

    @Mapping(target = "id", qualifiedByName = "StringToObjectId")
    @Mapping(target = "projectionDate", qualifiedByName = "stringToLdtList")
    @Mapping(target = "reservedSeats", qualifiedByName = "mapOfDto")
    public abstract MovieEntity movieDetailsToMovieEntity(MovieDetails movieSummary);

    @Mapping(target = "id", qualifiedByName = "objectIdToString")
    public abstract MovieSummary movieEntityToMovieSummary(MovieEntity movieEntity);

}

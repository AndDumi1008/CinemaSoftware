package com.example.cinemabackend.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketImpl implements TicketService {

    private Integer adult;
    private Integer child;
    private Integer retired;
    private Integer student;

    private static float adultPrice = 19.00f;
    private final float childPrice = adultPrice * 0.75f;   // -25% from adultPrice
    private final float retiredPrice = adultPrice * 0.5f; // -50% from adultPrice
    private final float studentPrice = adultPrice * 0.85f; // -15% from adultPrice

    @Override
    public String Compute() {
        float total = adult*adultPrice + child*childPrice + retired*retiredPrice + student*studentPrice;
        return "Total price to pay: " + total + "  RON";
    }
}

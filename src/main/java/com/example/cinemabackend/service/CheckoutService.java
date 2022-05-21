package com.example.cinemabackend.service;

import com.example.cinemabackend.dto.Checkout;
import com.example.cinemabackend.dto.MovieDetails;

public interface CheckoutService {

    Checkout update(Checkout checkout, String id);
}

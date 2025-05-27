package com.refundcalc.railwayrefund.controller;

public class RefundRequest {
    private String ticketType; // CONFIRMED, RAC, WL
    private String travelClass; // SL, 3AC, 2AC, etc.
    private long timeBeforeDeparture; // in hours
    private double ticketFare;

    // Getters and Setters
}
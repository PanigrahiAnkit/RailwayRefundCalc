package com.refundcalc.railwayrefund.model;

public class RefundRequest {
    private String ticketType; // "confirmed", "rac", "wl"
    private double ticketFare;
    private int timeBeforeDeparture; // in hours
    private String classType; // "SL", "1A", "2A", "3A", "3E"

    // Getters and Setters
    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(double ticketFare) {
        this.ticketFare = ticketFare;
    }

    public int getTimeBeforeDeparture() {
        return timeBeforeDeparture;
    }

    public void setTimeBeforeDeparture(int timeBeforeDeparture) {
        this.timeBeforeDeparture = timeBeforeDeparture;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
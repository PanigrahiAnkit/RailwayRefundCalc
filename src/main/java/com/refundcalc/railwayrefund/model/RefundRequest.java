package com.refundcalc.railwayrefund.model;

public class RefundRequest {
    private String ticketType; // "confirmed" or "waiting"
    private double ticketFare;
    private int timeBeforeDeparture; // in hours
    private String journeyClass; // Optional: SL, 3A, etc.
    private String passengerStatus; // Optional: WL, RAC, CNF

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

    public String getJourneyClass() {
        return journeyClass;
    }

    public void setJourneyClass(String journeyClass) {
        this.journeyClass = journeyClass;
    }

    public String getPassengerStatus() {
        return passengerStatus;
    }

    public void setPassengerStatus(String passengerStatus) {
        this.passengerStatus = passengerStatus;
    }
}
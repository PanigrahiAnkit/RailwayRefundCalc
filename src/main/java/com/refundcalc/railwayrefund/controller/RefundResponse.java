package com.refundcalc.railwayrefund.controller;

public class RefundResponse {
    private double refundAmount;
    private String message;

    public RefundResponse(double refundAmount, String message) {
        this.refundAmount = refundAmount;
        this.message = message;
    }

    // Getters
}
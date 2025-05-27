package com.refundcalc.railwayrefund.model;

public class RefundResponse {
    private double refundAmount;
    private String message;

    public RefundResponse(double refundAmount, String message) {
        this.refundAmount = refundAmount;
        this.message = message;
    }

    // Getters and Setters
    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
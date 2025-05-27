package com.refundcalc.railwayrefund.model;

public class RefundResponse {
    private double refundAmount;
    private String message;
    private String deductionDetails;
    private String suggestedCancellationTime;

    public RefundResponse(double refundAmount, String message, String deductionDetails, String suggestedCancellationTime) {
        this.refundAmount = refundAmount;
        this.message = message;
        this.deductionDetails = deductionDetails;
        this.suggestedCancellationTime = suggestedCancellationTime;
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

    public String getDeductionDetails() {
        return deductionDetails;
    }

    public void setDeductionDetails(String deductionDetails) {
        this.deductionDetails = deductionDetails;
    }

    public String getSuggestedCancellationTime() {
        return suggestedCancellationTime;
    }

    public void setSuggestedCancellationTime(String suggestedCancellationTime) {
        this.suggestedCancellationTime = suggestedCancellationTime;
    }
}
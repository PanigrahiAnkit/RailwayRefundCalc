package com.refundcalc.railwayrefund.utils;

import com.refundcalc.railwayrefund.model.RefundRequest;
import com.refundcalc.railwayrefund.model.RefundResponse;

public class RefundRuleEngine {

    public static RefundResponse calculateRefund(RefundRequest request) {
        double fare = request.getTicketFare();
        long hoursLeft = request.getTimeBeforeDeparture();
        String classType = request.getClassType().toUpperCase(); // "SL", "1A", "2A", "3A", "3E"
        String ticketType = request.getTicketType().toUpperCase(); // "CONFIRMED", "RAC", "WL"
        double deduction = 0;
        String message = "";
        String suggestedCancellationTime = "";

        if (ticketType.equals("CONFIRMED")) {
            if (hoursLeft > 48) {
                deduction = getFlatDeduction(classType);
                message = "Flat ₹" + deduction + " cancellation fee";
                suggestedCancellationTime = "Cancel just before 48 hours to pay flat fee of ₹" + deduction;
            } else if (hoursLeft > 12) {
                deduction = fare * 0.25;
                message = "25% cancellation fee (12-48 hrs before)";
                suggestedCancellationTime = "Cancel before 48 hours to get a lower cancellation fee";
            } else if (hoursLeft >= 4) {
                deduction = fare * 0.5;
                message = "50% cancellation fee (4-12 hrs before)";
                suggestedCancellationTime = "Cancel before 12 hours to get a lower cancellation fee";
            } else {
                deduction = fare;
                message = "No refund for cancellations within 4 hours of departure";
                suggestedCancellationTime = "No refund if cancelled within 4 hours";
            }
        } else if (ticketType.equals("RAC") || ticketType.equals("WL")) {
            if (hoursLeft >= 4) {
                deduction = 60;
                message = "₹60 cancellation fee for RAC/WL ticket";
                suggestedCancellationTime = "Cancel before 4 hours to get ₹60 cancellation fee";
            } else {
                deduction = fare;
                message = "No refund for cancellations within 4 hours";
                suggestedCancellationTime = "No refund if cancelled within 4 hours";
            }
        } else {
            return new RefundResponse(0, "Invalid ticket type entered", "", "");
        }

        double refund = Math.max(0, fare - deduction);
        String deductionDetails = "Deducted ₹" + deduction;

        return new RefundResponse(refund, message, deductionDetails, suggestedCancellationTime);
    }

    private static double getFlatDeduction(String classType) {
        return switch (classType) {
            case "1A" -> 240;
            case "2A" -> 200;
            case "3A", "3E" -> 180;
            case "SL" -> 120;
            default -> 120; // Default if unknown class
        };
    }
}
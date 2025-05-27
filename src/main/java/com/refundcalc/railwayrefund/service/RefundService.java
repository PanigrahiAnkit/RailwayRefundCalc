package com.refundcalc.railwayrefund.service;

import com.refundcalc.railwayrefund.model.RefundRequest;
import com.refundcalc.railwayrefund.model.RefundResponse;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public RefundResponse getRefundEstimate(RefundRequest request) {
        double fare = request.getTicketFare();
        String ticketType = request.getTicketType().toLowerCase();
        String journeyClass = request.getJourneyClass().toUpperCase();
        int hoursLeft = request.getTimeBeforeDeparture();

        double deduction = 0;
        String message = "";
        String suggestion = "";
        String deductionDetails = getDeductionTooltip(journeyClass);

        if ("confirmed".equals(ticketType)) {
            if (hoursLeft > 48) {
                deduction = getFlatDeduction(journeyClass);
                message = "Flat cancellation charges applied for >48 hrs.";
                suggestion = "Cancel before 48 hours for minimal deduction.";
            } else if (hoursLeft > 12) {
                deduction = fare * 0.25;
                message = "25% deduction for 12–48 hrs before journey.";
                suggestion = "To save more, cancel before 48 hrs.";
            } else if (hoursLeft >= 4) {
                deduction = fare * 0.5;
                message = "50% deduction for 4–12 hrs before journey.";
                suggestion = "To save more, cancel before 12 hrs.";
            } else {
                deduction = fare;
                message = "No refund under 4 hrs.";
                suggestion = "Avoid cancelling within 4 hrs.";
            }
        } else if ("rac".equals(ticketType) || "waiting".equals(ticketType) || "wl".equals(ticketType)) {
            if (hoursLeft >= 4) {
                deduction = 60;
                message = "₹60 deducted for RAC/WL if cancelled before 4 hrs.";
                suggestion = "Cancel before 4 hrs to get most refund.";
            } else {
                deduction = fare;
                message = "No refund under 4 hrs.";
                suggestion = "Avoid cancelling within 4 hrs.";
            }
        } else {
            return new RefundResponse(0, "Invalid ticket type", "", "");
        }

        double refundAmount = Math.max(0, fare - deduction);

        return new RefundResponse(refundAmount, message, deductionDetails, suggestion);
    }

    private double getFlatDeduction(String journeyClass) {
        switch (journeyClass) {
            case "SL": return 60;
            case "3A": return 180;
            case "3E": return 180;
            case "2A": return 200;
            case "1A": return 240;
            default: return 100;
        }
    }

    private String getDeductionTooltip(String journeyClass) {
        return """
                Deduction Chart as per IRCTC (for Confirmed Tickets):
                - 1A: ₹240
                - 2A: ₹200
                - 3A/3E: ₹180
                - SL: ₹60
                - RAC/WL: ₹60 if >4hrs left, else no refund
                - 25% deducted if cancelled 12–48 hrs before
                - 50% deducted if cancelled 4–12 hrs before
                - No refund < 4 hrs before
                """;
    }
}
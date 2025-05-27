package com.refundcalc.railwayrefund.utils;

import com.refundcalc.railwayrefund.controller.RefundRequest;
import com.refundcalc.railwayrefund.controller.RefundResponse;

public class RefundRuleEngine {

    public static RefundResponse calculateRefund(RefundRequest request) {
        double fare = request.getTicketFare();
        long hoursLeft = request.getTimeBeforeDeparture();
        String type = request.getTicketType().toUpperCase();
        double refund = 0;

        if (type.equals("CONFIRMED")) {
            if (hoursLeft > 48) refund = fare - 240;
            else if (hoursLeft > 12) refund = fare * 0.75;
            else if (hoursLeft >= 4) refund = fare * 0.5;
            else refund = 0;
        } else if (type.equals("RAC") || type.equals("WL")) {
            if (hoursLeft >= 4) refund = fare - 60;
            else refund = 0;
        } else {
            return new RefundResponse(0, "Invalid ticket type");
        }

        refund = Math.max(0, refund); // avoid negative
        return new RefundResponse(refund, "Estimated refund calculated");
    }
}
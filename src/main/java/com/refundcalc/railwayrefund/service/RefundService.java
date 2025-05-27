package com.refundcalc.railwayrefund.service;

import com.refundcalc.railwayrefund.model.RefundRequest;
import com.refundcalc.railwayrefund.model.RefundResponse;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public RefundResponse getRefundEstimate(RefundRequest request) {
        double fare = request.getTicketFare();
        String ticketType = request.getTicketType().toLowerCase();
        int hoursLeft = request.getTimeBeforeDeparture();

        double refund = 0;
        String message = "";

        if ("confirmed".equals(ticketType)) {
            if (hoursLeft >= 48) {
                refund = fare - 60;
                message = "Flat ₹60 cancellation fee";
            } else if (hoursLeft >= 12) {
                refund = fare - 100;
                message = "₹100 fee if cancelled 12-48 hrs before";
            } else if (hoursLeft >= 4) {
                refund = fare - 200;
                message = "₹200 fee if cancelled 4-12 hrs before";
            } else {
                refund = 0;
                message = "No refund if cancelled under 4 hrs";
            }
        } else if ("waiting".equals(ticketType)) {
            refund = fare;
            message = "Full refund for waiting list";
        } else {
            refund = 0;
            message = "Invalid ticket type";
        }

        return new RefundResponse(refund, message);
    }
}
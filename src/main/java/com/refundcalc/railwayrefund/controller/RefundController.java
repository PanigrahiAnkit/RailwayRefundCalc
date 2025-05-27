package com.refundcalc.railwayrefund.controller;


import com.refundcalc.railwayrefund.model.RefundRequest;
import com.refundcalc.railwayrefund.model.RefundResponse;
import com.refundcalc.railwayrefund.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refund")
@CrossOrigin(origins = "*")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @Autowired
    private PNRService pnrService;

    @PostMapping
    public RefundResponse calculateRefund(@RequestBody RefundRequest request) {
        return refundService.getRefundEstimate(request);
    }

    @GetMapping("/pnr/{pnr}")
    public RefundResponse calculateRefundFromPNR(@PathVariable String pnr,
                                                 @RequestParam double ticketFare) {
        try {
            JsonNode pnrData = pnrService.fetchPNRData(pnr);
            JsonNode dataNode = pnrData.path("data");

            String journeyClass = dataNode.path("journey_class").asText();
            String chartStatus = dataNode.path("chart_prepared").asText();
            String status = dataNode.path("passenger_status").get(0).path("booking_status").asText();
            String dojString = dataNode.path("date_of_journey").asText(); // e.g. "May 31, 2025 12:00:00 AM"

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH);
            LocalDateTime journeyDateTime = LocalDateTime.parse(dojString, formatter);
            long hoursLeft = Duration.between(LocalDateTime.now(), journeyDateTime).toHours();

            RefundRequest request = new RefundRequest();
            request.setTicketFare(ticketFare);
            request.setTimeBeforeDeparture((int) hoursLeft);

            if (status.contains("WL")) {
                request.setTicketType("waiting");
            } else {
                request.setTicketType("confirmed");
            }

            return refundService.getRefundEstimate(request);

        } catch (Exception e) {
            return new RefundResponse(0, "Failed to fetch PNR details: " + e.getMessage());
        }
    }
}
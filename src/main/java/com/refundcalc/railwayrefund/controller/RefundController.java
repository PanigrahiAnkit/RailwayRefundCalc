package com.refundcalc.railwayrefund.controller;

import com.refundcalc.railwayrefund.model.RefundRequest;
import com.refundcalc.railwayrefund.model.RefundResponse;
import com.refundcalc.railwayrefund.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refund")
@CrossOrigin(origins = "*") // For frontend access, adjust origin if needed
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping
    public RefundResponse calculateRefund(@RequestBody RefundRequest request) {
        return refundService.getRefundEstimate(request);
    }
}
package com.refundcalc.railwayrefund.service;

import com.refundcalc.railwayrefund.controller.RefundRequest;
import com.refundcalc.railwayrefund.controller.RefundResponse;
import com.refundcalc.railwayrefund.utils.RefundRuleEngine;
import org.springframework.stereotype.Service;

public class RefundService {

    public RefundResponse getRefundEstimate(RefundRequest request) {
        return RefundRuleEngine.calculateRefund(request);
    }
}
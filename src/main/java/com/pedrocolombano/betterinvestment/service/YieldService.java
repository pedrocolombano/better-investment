package com.pedrocolombano.betterinvestment.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class YieldService {

    public BigDecimal sumMonthlyYieldByPeriod(final BigDecimal yield,
                                                     final LocalDate startDate,
                                                     final LocalDate endDate) {
        long numberOfMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        BigDecimal monthlyYield = yield.divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_DOWN);
        return monthlyYield.multiply(BigDecimal.valueOf(numberOfMonths));
    }

}

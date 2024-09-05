package com.pedrocolombano.betterinvestment.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FinancialOperationTaxUtil {

    private static final int FINANCIAL_OPERATION_START_TAX = 96;

    private FinancialOperationTaxUtil() {

    }

    public static double getTaxByDaysDifference(final LocalDate startDate, final LocalDate endDate) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
        return daysDifference > 30 ? 0 : FINANCIAL_OPERATION_START_TAX - (daysDifference - 1) * 3;
    }

}

package com.pedrocolombano.betterinvestment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NumberUtil {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    private NumberUtil() {

    }

    public static BigDecimal getValueByPercentage(final BigDecimal value, final BigDecimal percentage, int scale) {
        return value.multiply(percentage)
                    .divide(ONE_HUNDRED, scale, RoundingMode.HALF_DOWN);
    }

    public static BigDecimal getValueByPercentage(final BigDecimal value, final double percentage, int scale) {
        return getValueByPercentage(value, BigDecimal.valueOf(percentage), scale);
    }

    public static BigDecimal getValueByPercentage(final BigDecimal value, final BigDecimal percentage) {
        return getValueByPercentage(value, percentage, 2);
    }

    public static BigDecimal getSummedMonthlyValueByPeriod(final BigDecimal value,
                                                           final LocalDate startDate,
                                                           final LocalDate endDate) {
        long numberOfMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        BigDecimal monthlyCdbYield = value.divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_DOWN);

        return monthlyCdbYield.multiply(BigDecimal.valueOf(numberOfMonths));
    }

}

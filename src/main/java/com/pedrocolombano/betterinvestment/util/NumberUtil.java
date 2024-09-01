package com.pedrocolombano.betterinvestment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

}

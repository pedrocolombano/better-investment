package com.pedrocolombano.betterinvestment.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IncomeTaxUtil {

    private IncomeTaxUtil() {

    }

    public static double getIncomeTaxByDaysDifference(LocalDate startDate, LocalDate endDate) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);

        if (daysDifference < 181) {
            return 22.5;
        } else if (daysDifference < 361) {
            return 20.0;
        } else if (daysDifference < 721) {
            return 17.5;
        }

        return 15.0;
    }

}

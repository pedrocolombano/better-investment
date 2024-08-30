package com.pedrocolombano.betterinvestment.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IncomeTaxUtilTest {

    @Test
    void getIncomeTaxShouldReturnCorrectIncomeTaxWhenDaysDifferenceIsLessThanHalfAnYear() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(180);

        double result = IncomeTaxUtil.getIncomeTaxByDaysDifference(startDate, endDate);

        Assertions.assertEquals(22.5, result);
    }

    @Test
    void getIncomeTaxShouldReturnCorrectIncomeTaxWhenDaysDifferenceIsBetweenHalfAnYearAndOneYear() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(200);

        double result = IncomeTaxUtil.getIncomeTaxByDaysDifference(startDate, endDate);

        Assertions.assertEquals(20.0, result);
    }

    @Test
    void getIncomeTaxShouldReturnCorrectIncomeTaxWhenDaysDifferenceIsBetweenOneYearAndTwoYears() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(600);

        double result = IncomeTaxUtil.getIncomeTaxByDaysDifference(startDate, endDate);

        Assertions.assertEquals(17.5, result);
    }

    @Test
    void getIncomeTaxShouldReturnCorrectIncomeTaxWhenDaysDifferenceIsMoreThanTwoYears() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(800);

        double result = IncomeTaxUtil.getIncomeTaxByDaysDifference(startDate, endDate);

        Assertions.assertEquals(15.0, result);
    }

}

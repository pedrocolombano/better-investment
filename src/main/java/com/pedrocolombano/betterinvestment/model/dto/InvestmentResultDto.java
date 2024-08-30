package com.pedrocolombano.betterinvestment.model.dto;

import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentResultDto {

    private String bank;
    private String name;
    private BigDecimal amount;
    private BigDecimal monthlyReturn;
    private BigDecimal yearlyReturn;
    private InvestmentType investmentType;
    private LocalDate startDate;
    private LocalDate endDate;

    public InvestmentResultDto(InvestmentDto investment, BigDecimal investmentReturn) {
        BeanUtils.copyProperties(investment, this);
        this.yearlyReturn = investmentReturn;
        this.monthlyReturn = investmentReturn.divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getAmount() {
        return amount.setScale(4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getMonthlyReturn() {
        return monthlyReturn.setScale(4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getYearlyReturn() {
        return yearlyReturn.setScale(4, RoundingMode.HALF_DOWN);
    }
}

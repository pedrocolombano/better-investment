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

    private BigDecimal yield;
    private BigDecimal amount;
    private BigDecimal investmentReturn;
    private InvestmentType investmentType;
    private LocalDate startDate;
    private LocalDate endDate;

    public InvestmentResultDto(InvestmentDto investment, BigDecimal investmentReturn) {
        BeanUtils.copyProperties(investment, this);
        this.investmentReturn = investmentReturn;
    }

    public BigDecimal getAmount() {
        return amount.setScale(4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getInvestmentReturn() {
        return investmentReturn.setScale(4, RoundingMode.HALF_DOWN);
    }
}

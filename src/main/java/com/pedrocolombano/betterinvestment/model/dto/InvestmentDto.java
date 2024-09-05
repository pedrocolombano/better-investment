package com.pedrocolombano.betterinvestment.model.dto;

import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDto {

    private String bank;
    private String name;
    private BigDecimal amount;
    private BigDecimal cdbYield;
    private BigDecimal lciYield;
    private BigDecimal selicYield;
    private LocalDate startDate;
    private LocalDate endDate;
    private InvestmentType investmentType;

}

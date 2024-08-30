package com.pedrocolombano.betterinvestment.strategy;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;

import java.math.BigDecimal;

public interface InvestmentStrategy {

    InvestmentResultDto getResult(InvestmentDto investment, BigDecimal cdiYield);
    InvestmentType getStrategyName();

}

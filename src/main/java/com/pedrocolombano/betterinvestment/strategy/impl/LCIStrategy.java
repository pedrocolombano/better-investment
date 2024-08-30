package com.pedrocolombano.betterinvestment.strategy.impl;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class LCIStrategy implements InvestmentStrategy {

    @Override
    public InvestmentResultDto getResult(final InvestmentDto investment, BigDecimal cdiYield) {
        BigDecimal investmentReturn = getInvestmentReturn(investment.getAmount(), cdiYield, investment.getLciYield());
        return new InvestmentResultDto(investment, investmentReturn);
    }

    private BigDecimal getInvestmentReturn(final BigDecimal amount, final BigDecimal cdiYield, final BigDecimal lciYield) {
        BigDecimal percentageDivisor = BigDecimal.valueOf(100);

        BigDecimal investmentYield = cdiYield.multiply(lciYield)
                                             .divide(percentageDivisor, RoundingMode.DOWN);

        return amount.multiply(investmentYield)
                     .divide(percentageDivisor, RoundingMode.HALF_DOWN);
    }

    @Override
    public InvestmentType getStrategyName() {
        return InvestmentType.LCI;
    }

}

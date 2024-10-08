package com.pedrocolombano.betterinvestment.strategy.impl;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import com.pedrocolombano.betterinvestment.service.YieldService;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import com.pedrocolombano.betterinvestment.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class LCIStrategyImpl implements InvestmentStrategy {

    private final YieldService yieldService;

    @Override
    public InvestmentResultDto getResult(final InvestmentDto investment, BigDecimal cdiYield) {
        BigDecimal investmentReturn = getInvestmentReturn(investment, cdiYield, investment.getLciYield());
        return new InvestmentResultDto(investment, investmentReturn);
    }

    private BigDecimal getInvestmentReturn(final InvestmentDto investment, final BigDecimal cdiYield, final BigDecimal lciYield) {
        BigDecimal investmentYield = NumberUtil.getValueByPercentage(cdiYield, lciYield, 4);
        BigDecimal totalInvestmentYield = yieldService.sumMonthlyYieldByPeriod(investmentYield, investment.getStartDate(), investment.getEndDate());

        return investment.getAmount().multiply(totalInvestmentYield)
                                     .divide(NumberUtil.ONE_HUNDRED, RoundingMode.HALF_DOWN);
    }

    @Override
    public InvestmentType getStrategyName() {
        return InvestmentType.LCI;
    }

}

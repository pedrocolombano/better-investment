package com.pedrocolombano.betterinvestment.service;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import com.pedrocolombano.betterinvestment.strategy.factory.InvestmentStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private static final BigDecimal CDI_YIELD = BigDecimal.valueOf(11.5);

    private final InvestmentStrategyFactory investmentStrategyFactory;

    public List<InvestmentResultDto> getInvestmentResults(final List<InvestmentDto> investments) {
        return investments.stream()
                          .map(this::getResult)
                          .toList();
    }

    private InvestmentResultDto getResult(final InvestmentDto investment) {
        InvestmentStrategy strategy = investmentStrategyFactory.getStrategy(investment.getInvestmentType());
        return strategy.getResult(investment, CDI_YIELD);
    }

}

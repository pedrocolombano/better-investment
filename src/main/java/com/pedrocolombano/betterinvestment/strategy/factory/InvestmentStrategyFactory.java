package com.pedrocolombano.betterinvestment.strategy.factory;

import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InvestmentStrategyFactory {

    private final Map<InvestmentType, InvestmentStrategy> strategyMap;

    public InvestmentStrategyFactory(List<InvestmentStrategy> strategies) {
        strategyMap = new HashMap<>();
        strategies.forEach(strategy -> strategyMap.put(strategy.getStrategyName(), strategy));
    }

    public InvestmentStrategy getStrategy(InvestmentType type) {
        return strategyMap.get(type);
    }
}

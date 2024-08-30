package com.pedrocolombano.betterinvestment.strategy.impl;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import com.pedrocolombano.betterinvestment.util.FinancialOperationTaxUtil;
import com.pedrocolombano.betterinvestment.util.IncomeTaxUtil;
import com.pedrocolombano.betterinvestment.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SelicStrategyImpl implements InvestmentStrategy {

    @Override
    public InvestmentResultDto getResult(final InvestmentDto investment, final BigDecimal cdiYield) {
        double incomeTax = IncomeTaxUtil.getIncomeTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());
        double financialOperationTax = FinancialOperationTaxUtil.getTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());
        BigDecimal investmentReturn = getInvestmentReturn(investment, incomeTax, financialOperationTax);
        return new InvestmentResultDto(investment, investmentReturn);
    }

    private BigDecimal getInvestmentReturn(final InvestmentDto investment,
                                           final double incomeTax,
                                           final double financialOperationTax) {

        BigDecimal totalSelicYield = NumberUtil.getSummedMonthlyValueByPeriod(investment.getSelicYield(), investment.getStartDate(), investment.getEndDate());
        BigDecimal grossInvestmentReturn = NumberUtil.getValueByPercentage(investment.getAmount(), totalSelicYield, 4);
        BigDecimal incomeTaxValue = NumberUtil.getValueByPercentage(grossInvestmentReturn, incomeTax, 4);
        BigDecimal financialOperationTaxValue = NumberUtil.getValueByPercentage(grossInvestmentReturn, financialOperationTax, 4);

        return grossInvestmentReturn.subtract(incomeTaxValue)
                                    .subtract(financialOperationTaxValue);
    }

    @Override
    public InvestmentType getStrategyName() {
        return InvestmentType.SELIC;
    }
}

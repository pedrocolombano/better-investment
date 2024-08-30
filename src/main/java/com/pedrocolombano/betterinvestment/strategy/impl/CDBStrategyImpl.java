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
import java.math.RoundingMode;

@Service
public class CDBStrategyImpl implements InvestmentStrategy {

    @Override
    public InvestmentResultDto getResult(final InvestmentDto investment, final BigDecimal cdiYield) {
        double incomeTax = IncomeTaxUtil.getIncomeTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());
        double financialOperationTax = FinancialOperationTaxUtil.getTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());

        BigDecimal calculatedCdbYield = investment.getCdbYield()
                                                  .divide(NumberUtil.ONE_HUNDRED, 4, RoundingMode.HALF_DOWN);

        BigDecimal cdbYield = cdiYield.multiply(calculatedCdbYield);
        BigDecimal investmentReturn = getInvestmentReturn(investment, cdbYield, incomeTax, financialOperationTax);

        return new InvestmentResultDto(investment, investmentReturn);
    }

    private BigDecimal getInvestmentReturn(final InvestmentDto investment,
                                           final BigDecimal cdbYield,
                                           final double incomeTax,
                                           final double financialOperationTax) {

        BigDecimal totalCdbYield = NumberUtil.getSummedMonthlyValueByPeriod(cdbYield, investment.getStartDate(), investment.getEndDate());
        BigDecimal grossInvestmentReturn =  NumberUtil.getValueByPercentage(investment.getAmount(), totalCdbYield, 4);
        BigDecimal incomeTaxValue = NumberUtil.getValueByPercentage(grossInvestmentReturn, incomeTax, 2);
        BigDecimal financialOperationTaxValue = NumberUtil.getValueByPercentage(grossInvestmentReturn, financialOperationTax, 2);

        return grossInvestmentReturn.subtract(incomeTaxValue)
                                    .subtract(financialOperationTaxValue);
    }

    @Override
    public InvestmentType getStrategyName() {
        return InvestmentType.CDB;
    }

}

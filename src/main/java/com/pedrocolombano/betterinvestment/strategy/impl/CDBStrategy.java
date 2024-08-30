package com.pedrocolombano.betterinvestment.strategy.impl;

import com.pedrocolombano.betterinvestment.model.dto.InvestmentDto;
import com.pedrocolombano.betterinvestment.model.dto.InvestmentResultDto;
import com.pedrocolombano.betterinvestment.model.enumerated.InvestmentType;
import com.pedrocolombano.betterinvestment.strategy.InvestmentStrategy;
import com.pedrocolombano.betterinvestment.util.FinancialOperationTaxUtil;
import com.pedrocolombano.betterinvestment.util.IncomeTaxUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CDBStrategy implements InvestmentStrategy {

    @Override
    public InvestmentResultDto getResult(final InvestmentDto investment, final BigDecimal cdiYield) {
        double incomeTax = IncomeTaxUtil.getIncomeTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());
        double financialOperationTax = FinancialOperationTaxUtil.getTaxByDaysDifference(investment.getStartDate(), investment.getEndDate());

        BigDecimal cdiDifference = cdiYield.multiply(investment.getCdbYield()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN);
        BigDecimal cdbYield = cdiYield.add(cdiDifference);
        BigDecimal investmentReturn = getInvestmentReturn(investment.getAmount(), cdbYield, incomeTax, financialOperationTax);

        return new InvestmentResultDto(investment, investmentReturn);
    }

    private BigDecimal getInvestmentReturn(final BigDecimal amount,
                                           final BigDecimal cdbYield,
                                           final double incomeTax,
                                           final double financialOperationTax) {
        BigDecimal percentageDivisor = BigDecimal.valueOf(100);


        BigDecimal grossInvestmentReturn = amount.multiply(cdbYield)
                                                 .divide(percentageDivisor, RoundingMode.HALF_DOWN);

        BigDecimal incomeTaxValue = grossInvestmentReturn.multiply(BigDecimal.valueOf(incomeTax))
                                                         .divide(percentageDivisor, RoundingMode.HALF_DOWN);

        BigDecimal financialOperationTaxValue = grossInvestmentReturn.multiply(BigDecimal.valueOf(financialOperationTax))
                                                                     .divide(percentageDivisor, RoundingMode.HALF_DOWN);

        return grossInvestmentReturn.subtract(incomeTaxValue)
                                    .subtract(financialOperationTaxValue);
    }

    @Override
    public InvestmentType getStrategyName() {
        return InvestmentType.CDB;
    }

}

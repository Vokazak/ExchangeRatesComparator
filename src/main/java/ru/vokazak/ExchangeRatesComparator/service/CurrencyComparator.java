package ru.vokazak.ExchangeRatesComparator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
@RequiredArgsConstructor
public class CurrencyComparator {

    @Value("${defaultCurrency.code}")
    private String defaultCurrencyCode;

    private final CurrencyAnalyzer currencyAnalyzer;

    public int compareWithDefaultCurrency(String currencyCode) throws ExchangeRatesComparatorException {

        BigDecimal oldRatio = currencyAnalyzer.getYesterdayRate(currencyCode)
                .divide(currencyAnalyzer.getYesterdayRate(defaultCurrencyCode), MathContext.DECIMAL32);

        BigDecimal newRatio = currencyAnalyzer.getCurrentRate(currencyCode)
                .divide(currencyAnalyzer.getCurrentRate(defaultCurrencyCode), MathContext.DECIMAL32);

        return newRatio.compareTo(oldRatio);
    }
}

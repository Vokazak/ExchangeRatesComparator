package ru.vokazak.ExchangeRatesComparator.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vokazak.ExchangeRatesComparator.client.ExchangeRateClient;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CurrencyAnalyzer {

    @Value("${openExchangeRates.id}")
    @Setter
    private String appId;

    private final ExchangeRateClient client;

    public BigDecimal getYesterdayRate(String currencyCode) throws ExchangeRatesComparatorException {
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        String yesterdayDate = new SimpleDateFormat("yyyy-MM-dd").format(Date.from(yesterday));

        JsonObject rates = client
                .findHistoricalCurrencyRates(yesterdayDate, appId)
                .getRates();

        return validateCurrency(rates, currencyCode);
    }

    public BigDecimal getCurrentRate(String currencyCode) throws ExchangeRatesComparatorException {
        JsonObject rates = client
                .findLatestCurrencyRates(appId)
                .getRates();

        return validateCurrency(rates, currencyCode);
    }

    private BigDecimal validateCurrency(JsonObject rates, String currencyCode) throws ExchangeRatesComparatorException {
        JsonElement currencyElement = rates.get(currencyCode);

        if (currencyElement == null) {
            throw new ExchangeRatesComparatorException("Invalid currency code \"" + currencyCode + '\"');
        }

        return currencyElement.getAsBigDecimal();
    }

}

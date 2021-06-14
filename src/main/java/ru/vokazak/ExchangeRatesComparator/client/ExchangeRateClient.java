package ru.vokazak.ExchangeRatesComparator.client;

import feign.Param;
import feign.RequestLine;
import ru.vokazak.ExchangeRatesComparator.pojo.CurrenciesRates;

public interface ExchangeRateClient {

    @RequestLine("GET /latest.json/?app_id={appId}")
    CurrenciesRates findLatestCurrencyRates(@Param("appId") String appId);

    @RequestLine("GET /historical/{date}.json/?app_id={appId}")
    CurrenciesRates findHistoricalCurrencyRates(@Param("date") String date, @Param("appId") String appId);
}

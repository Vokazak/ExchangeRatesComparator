package ru.vokazak.ExchangeRatesComparator.service;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vokazak.ExchangeRatesComparator.client.ExchangeRateClient;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.CurrenciesRates;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CurrencyAnalyzer.class)
@RunWith(SpringRunner.class)
class CurrencyAnalyzerTest {

    CurrencyAnalyzer currencyAnalyzer;

    @MockBean
    ExchangeRateClient exchangeRateClient;

    private static final String APP_ID = "SAMPLE_ID";

    CurrenciesRates yesterdayRates;
    CurrenciesRates todayRates;

    JsonObject yesterdayUsdRate;
    JsonObject todayUsdRate;

    @BeforeEach
    void setUp() {

        yesterdayUsdRate = new JsonObject();
        yesterdayUsdRate.addProperty("USD", new BigDecimal("1234"));
        todayUsdRate = new JsonObject();
        todayUsdRate.addProperty("USD", new BigDecimal("1000"));

        yesterdayRates = new CurrenciesRates();
        yesterdayRates.setRates(yesterdayUsdRate);
        todayRates = new CurrenciesRates();
        todayRates.setRates(todayUsdRate);

        String yesterdayDate = new SimpleDateFormat("yyyy-MM-dd")
                .format(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)));

        when(exchangeRateClient.findHistoricalCurrencyRates(yesterdayDate, APP_ID))
                .thenReturn(yesterdayRates);
        when(exchangeRateClient.findLatestCurrencyRates(APP_ID))
                .thenReturn(todayRates);

        currencyAnalyzer = new CurrencyAnalyzer(exchangeRateClient);
        currencyAnalyzer.setAppId(APP_ID);
    }

    @Test
    void getTodayRate() throws ExchangeRatesComparatorException {
        BigDecimal usd = currencyAnalyzer.getCurrentRate("USD");
        assertEquals(new BigDecimal("1000"), usd);
    }

    @Test
    void getYesterdayRate() throws ExchangeRatesComparatorException {
        BigDecimal usd = currencyAnalyzer.getYesterdayRate("USD");
        assertEquals(new BigDecimal("1234"), usd);
    }

    @Test
    void getNoneExistingRate()  {
        ExchangeRatesComparatorException exception = assertThrows(ExchangeRatesComparatorException.class,
                () -> currencyAnalyzer.getCurrentRate("random string")
        );

        assertEquals("Invalid currency code \"random string\"", exception.getMessage());
    }
}
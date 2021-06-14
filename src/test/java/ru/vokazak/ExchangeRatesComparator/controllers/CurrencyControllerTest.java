package ru.vokazak.ExchangeRatesComparator.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.GifObject;
import ru.vokazak.ExchangeRatesComparator.service.CurrencyComparator;
import ru.vokazak.ExchangeRatesComparator.service.GifFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CurrencyController.class)
@RunWith(SpringRunner.class)
class CurrencyControllerTest {

    CurrencyController currencyController;

    @MockBean
    CurrencyComparator currencyComparator;

    @MockBean
    GifFinder gifFinder;

    private static final String GROWING_CURRENCY_CODE = "SAMPLE_CODE_1";
    private static final String FALLING_CURRENCY_CODE = "SAMPLE_CODE_2";
    private static final String NON_EXISTENT_CODE = "SAMPLE_CODE_3";

    GifObject rich;
    GifObject broke;

    @BeforeEach
    void setUp() throws ExchangeRatesComparatorException {
        rich = new GifObject();
        rich.setUrl("rich_url");
        broke = new GifObject();
        broke.setUrl("broke_url");

        when(currencyComparator.compareWithDefaultCurrency(GROWING_CURRENCY_CODE))
                .thenReturn(1);
        when(currencyComparator.compareWithDefaultCurrency(FALLING_CURRENCY_CODE))
                .thenReturn(-1);
        when(currencyComparator.compareWithDefaultCurrency(NON_EXISTENT_CODE))
                .thenThrow(ExchangeRatesComparatorException.class);

        when(gifFinder.getGifByTag("rich"))
                .thenReturn(rich);
        when(gifFinder.getGifByTag("broke"))
                .thenReturn(broke);

        currencyController = new CurrencyController(currencyComparator, gifFinder);
        currencyController.setCurrencyGrowsKeyword("rich");
        currencyController.setCurrencyFallsKeyword("broke");
    }

    @Test
    void gifByCurrencyState() throws ExchangeRatesComparatorException {
        GifObject gifObject1 = currencyController.gifByCurrencyState(GROWING_CURRENCY_CODE);
        GifObject gifObject2 = currencyController.gifByCurrencyState(FALLING_CURRENCY_CODE);

        assertEquals(rich, gifObject1);
        assertEquals(broke, gifObject2);
    }

    @Test
    void gifByNonExistentCurrencyState() {

        ExchangeRatesComparatorException exception = assertThrows(
                ExchangeRatesComparatorException.class,
                () -> currencyController.gifByCurrencyState(NON_EXISTENT_CODE)
        );

        assertEquals(exception.getMessage(),"Exception while getting gif by currency state");
    }
}
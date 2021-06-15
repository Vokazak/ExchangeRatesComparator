package ru.vokazak.ExchangeRatesComparator.controllers;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vokazak.ExchangeRatesComparator.service.CurrencyComparator;
import ru.vokazak.ExchangeRatesComparator.service.GifFinder;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.GifObject;

@RestController
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "keyword")
public class CurrencyController {

    private final CurrencyComparator currencyComparator;
    private final GifFinder gifFinder;

    @Setter
    private String currencyGrows;
    @Setter
    private String currencyFalls;

    @GetMapping("/currency/{currencyCode}")
    public GifObject gifByCurrencyState(@PathVariable String currencyCode) throws ExchangeRatesComparatorException {

        int comparingResult = currencyComparator.compareWithDefaultCurrency(currencyCode);

        if (comparingResult >= 0) {
            return gifFinder.getGifByTag(currencyGrows);
        } else {
            return gifFinder.getGifByTag(currencyFalls);
        }
    }
}

package ru.vokazak.ExchangeRatesComparator.controllers;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vokazak.ExchangeRatesComparator.service.CurrencyComparator;
import ru.vokazak.ExchangeRatesComparator.service.GifFinder;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.GifObject;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyComparator currencyComparator;
    private final GifFinder gifFinder;

    @Value("${keyword.currencyGrows}")
    @Setter
    private String currencyGrowsKeyword;
    @Value("${keyword.currencyFalls}")
    @Setter
    private String currencyFallsKeyword;

    @GetMapping("/currency/{currencyCode}")
    public GifObject gifByCurrencyState(@PathVariable String currencyCode) throws ExchangeRatesComparatorException {

        try {
            int comparingResult = currencyComparator.compareWithDefaultCurrency(currencyCode);
            if (comparingResult >= 0) {
                return gifFinder.getGifByTag(currencyGrowsKeyword);
            } else {
                return gifFinder.getGifByTag(currencyFallsKeyword);
            }

        } catch (ExchangeRatesComparatorException e) {
            throw new ExchangeRatesComparatorException("Exception while getting gif by currency state", e);
        }
    }
}

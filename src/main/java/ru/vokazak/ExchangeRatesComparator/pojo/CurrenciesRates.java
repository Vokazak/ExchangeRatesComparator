package ru.vokazak.ExchangeRatesComparator.pojo;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class CurrenciesRates {
    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private JsonObject rates;
}

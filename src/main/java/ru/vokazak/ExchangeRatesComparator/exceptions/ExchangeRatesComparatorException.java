package ru.vokazak.ExchangeRatesComparator.exceptions;

public class ExchangeRatesComparatorException extends Exception {
    public ExchangeRatesComparatorException(String message, Throwable e) {
        super(message, e);
    }
    public ExchangeRatesComparatorException(String message) {super(message);}
}

package ru.vokazak.ExchangeRatesComparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;

@SpringBootApplication
public class ExchangeRatesComparatorApplication {

	public static void main(String[] args) throws ExchangeRatesComparatorException {
		SpringApplication.run(ExchangeRatesComparatorApplication.class, args);
	}

}

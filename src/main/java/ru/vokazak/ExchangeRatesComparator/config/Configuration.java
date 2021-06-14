package ru.vokazak.ExchangeRatesComparator.config;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.vokazak.ExchangeRatesComparator.client.ExchangeRateClient;
import ru.vokazak.ExchangeRatesComparator.client.GiphyClient;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.vokazak.ExchangeRatesComparator")
@PropertySource("classpath:comparator.properties")
public class Configuration {

    @Value("${openExchangeRates.url}")
    private String openExchangeRatesUrl;

    @Bean
    ExchangeRateClient exchangeRateClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExchangeRateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ExchangeRateClient.class, openExchangeRatesUrl);
    }

    @Value("${giphy.url}")
    private String giphyUrl;

    @Bean
    GiphyClient giphyClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(GiphyClient.class))
                .logLevel(Logger.Level.FULL)
                .target(GiphyClient.class, giphyUrl);
    }
}

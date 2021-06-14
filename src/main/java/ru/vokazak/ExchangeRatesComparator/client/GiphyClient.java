package ru.vokazak.ExchangeRatesComparator.client;

import feign.Param;
import feign.RequestLine;
import ru.vokazak.ExchangeRatesComparator.pojo.GifReply;

public interface GiphyClient {

    @RequestLine("GET /random?api_key={apiKey}&tag={tag}&rating=g")
    GifReply findRandomGifByTag(@Param("tag") String tag, @Param("apiKey") String apiKey);

}

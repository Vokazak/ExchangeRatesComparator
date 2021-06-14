package ru.vokazak.ExchangeRatesComparator.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vokazak.ExchangeRatesComparator.client.GiphyClient;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.GifObject;
import ru.vokazak.ExchangeRatesComparator.pojo.GifReply;

@Component
@RequiredArgsConstructor
public class GifFinder {

    @Value("${giphy.key}")
    @Setter
    private String appKey;

    private final GiphyClient client;

    public GifObject getGifByTag(String tag) throws ExchangeRatesComparatorException {
        GifReply gifReply = client.findRandomGifByTag(tag, appKey);

        int status = gifReply.getMeta().getStatus();
        if (status != 200) {
            throw new ExchangeRatesComparatorException("Http status " + status + ", " + gifReply.getMeta().getMsg());
        }

        return gifReply.getData();
    }
}

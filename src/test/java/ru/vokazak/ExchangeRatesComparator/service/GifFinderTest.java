package ru.vokazak.ExchangeRatesComparator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vokazak.ExchangeRatesComparator.client.GiphyClient;
import ru.vokazak.ExchangeRatesComparator.exceptions.ExchangeRatesComparatorException;
import ru.vokazak.ExchangeRatesComparator.pojo.GifObject;
import ru.vokazak.ExchangeRatesComparator.pojo.GifReply;
import ru.vokazak.ExchangeRatesComparator.pojo.MetaObject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(GifFinder.class)
@RunWith(SpringRunner.class)
class GifFinderTest {

    GifFinder gifFinder;

    @MockBean
    GiphyClient giphyClient;

    private static final String API_KEY = "KEY";
    private static final String TAG = "TAG";
    private static final String GIF_URL = "successful_url";

    GifReply gifReply;

    @BeforeEach
    void setUp() {
        GifObject gif = new GifObject();
        gif.setUrl(GIF_URL);

        MetaObject meta = new MetaObject();
        meta.setStatus(200);

        gifReply = new GifReply();
        gifReply.setData(gif);
        gifReply.setMeta(meta);

        when(giphyClient.findRandomGifByTag(TAG, API_KEY))
                .thenReturn(gifReply);

        gifFinder = new GifFinder(giphyClient);
        gifFinder.setAppKey(API_KEY);
    }

    @Test
    void gifByTagTest() throws ExchangeRatesComparatorException {
        GifObject gif = gifFinder.getGifByTag(TAG);
        assertEquals(GIF_URL, gif.getUrl());
    }


}
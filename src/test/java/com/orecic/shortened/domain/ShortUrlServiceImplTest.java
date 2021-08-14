package com.orecic.shortened.domain;

import com.orecic.shortened.application.data.ShortUrlRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShortUrlServiceImplTest {

    private ShortUrlService shortUrlService;

    @BeforeEach
    public void init() {
        shortUrlService = new ShortUrlServiceImpl();
    }

    @Test
    public void shouldGenerateShortUrlWithSuccess() {
        // given
        var request = new ShortUrlRequest("https://www.educative.io/courses/grokking-the-system-design-interview/m2ygV4E81AR");

        // when
        var shortenedUrl = shortUrlService.getShortUrl(request);

        // then
        Assertions.assertEquals("short-url", shortenedUrl.shortUrl());
    }
}

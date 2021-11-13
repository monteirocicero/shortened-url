package com.orecic.shortened.domain;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.domain.data.UrlDAO;
import com.orecic.shortened.domain.service.ShortUrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ShortUrlServiceImplTest {


    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private UrlDAO urlDAO;

    @Test
    public void generateShortUrlAndSaveOnDatabase() {
        // given
        var request = new ShortUrlRequest("https://www.educative.io/courses/grokking-the-system-design-interview/m2ygV4E81AR");
        var expectDomain = "http://localhost:8080/shortened-url";

        // when
        var shortenedUrl = shortUrlService.buildShortUrl(request);

        // then
        Assertions.assertNotNull(shortenedUrl.getShortUrl());
        Assertions.assertEquals(expectDomain, shortenedUrl.domainApp());
    }
}

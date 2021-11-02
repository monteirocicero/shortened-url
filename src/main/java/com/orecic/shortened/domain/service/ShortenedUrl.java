package com.orecic.shortened.domain.service;

import com.orecic.shortened.application.data.ShortUrlRequest;

public class ShortenedUrl {
    private final String alias;
    private final ShortUrlRequest shortUrlRequest;

    public ShortenedUrl(String alias, ShortUrlRequest shortUrlRequest) {
        this.alias = alias;
        this.shortUrlRequest = shortUrlRequest;
    }

    public String getAlias() {
        return alias;
    }

    public ShortUrlRequest getShortUrlRequest() {
        return shortUrlRequest;
    }
}

package com.orecic.shortened.application.data;


import javax.validation.constraints.NotEmpty;

public record ShortUrlRequest(@NotEmpty String largeUrl, String alias, Long timeToExpiration) {

    public ShortUrlRequest(String largeUrl) {
        this(largeUrl, null, null);
    }
}

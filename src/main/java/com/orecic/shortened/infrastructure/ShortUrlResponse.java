package com.orecic.shortened.infrastructure;

public record ShortUrlResponse(String domainApp, String alias) {

    public String getShortUrl() {
        return new StringBuilder().append(domainApp).append("/").append(alias).toString();
    }
}

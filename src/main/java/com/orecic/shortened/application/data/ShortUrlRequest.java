package com.orecic.shortened.application.data;


import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Objects;

public record ShortUrlRequest(@NotEmpty String originalUrl, String alias, Long timeToExpiration) {

    public ShortUrlRequest(String originalUrl) {
        this(originalUrl, null, null);
    }

    public Timestamp convertMillisToTimestamp() {

        if (Objects.isNull(timeToExpiration)) {
            return null;
        }

        return new Timestamp(this.timeToExpiration);
    }
}

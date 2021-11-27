package com.orecic.shortened.application.data;


import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record ShortUrlRequest(@NotEmpty String originalUrl, String alias, Long timeToExpiration) {

    public ShortUrlRequest(String originalUrl) {
        this(originalUrl, null, null);
    }

    public Timestamp convertMillisToTimestamp() {

        if (Objects.isNull(timeToExpiration)) {
            Instant instant = Instant.now().plus(Duration.ofMinutes(15));
            long timeStampMillis = instant.toEpochMilli();
            return new Timestamp(timeStampMillis);
        }

        return new Timestamp(this.timeToExpiration);
    }
}

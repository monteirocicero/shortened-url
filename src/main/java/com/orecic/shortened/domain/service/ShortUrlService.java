package com.orecic.shortened.domain.service;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.infrastructure.ShortUrlResponse;

public interface ShortUrlService {
    ShortUrlResponse getShortUrl(ShortUrlRequest shortUrlRequest);
}

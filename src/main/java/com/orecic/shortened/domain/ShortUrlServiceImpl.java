package com.orecic.shortened.domain;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.infrastructure.ShortUrlResponse;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Override
    public ShortUrlResponse getShortUrl(ShortUrlRequest shortUrlRequest) {
        return new ShortUrlResponse("short-url");
    }
}

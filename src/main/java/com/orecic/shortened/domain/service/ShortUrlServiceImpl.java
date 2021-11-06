package com.orecic.shortened.domain.service;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.domain.data.UrlDAO;
import com.orecic.shortened.domain.entity.UrlEntity;
import com.orecic.shortened.infrastructure.ShortKeyGenerator;
import com.orecic.shortened.infrastructure.ShortUrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

    private UrlDAO urlDAO;

    @Value("${domain.url}")
    private String domainApp;

    @Autowired
    public ShortUrlServiceImpl(UrlDAO urlDao) {
        this.urlDAO = urlDao;
    }

    @Override
    public ShortUrlResponse getShortUrl(ShortUrlRequest shortUrlRequest) {
        logger.info("m=getShortUrl msg=generate-short-url request={}", shortUrlRequest.originalUrl());

        var alias = new ShortKeyGenerator(shortUrlRequest.originalUrl()).getKey();

        urlDAO.save(new UrlEntity(alias.get(), shortUrlRequest.originalUrl(), shortUrlRequest.convertMillisToTimestamp()));

        return new ShortUrlResponse(domainApp, alias.get());
    }
}

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ShortUrlResponse buildShortUrl(ShortUrlRequest shortUrlRequest) {
        logger.info("m=buildShortUrl msg=generate-short-url request={}", shortUrlRequest.originalUrl());

        if (StringUtils.hasLength(shortUrlRequest.alias())) {
            if (StringUtils.hasLength(urlDAO.alreadyExistsAlias(shortUrlRequest.alias()))) {
                throw new IllegalArgumentException("Already exists alias with this name.");
            }

            urlDAO.save(new UrlEntity(shortUrlRequest.alias(), shortUrlRequest.originalUrl(), handleTimeToExpiration(shortUrlRequest)));
            redisTemplate.opsForHash().put("URL", shortUrlRequest.alias(), shortUrlRequest.originalUrl());
            return new ShortUrlResponse(domainApp, shortUrlRequest.alias());

        } else {
            var alias = new ShortKeyGenerator(shortUrlRequest.originalUrl()).getKey();
            logger.info("m=buildShortUrl msg=generate-short-url request={}", shortUrlRequest.originalUrl());

            urlDAO.save(new UrlEntity(alias.get(), shortUrlRequest.originalUrl(), handleTimeToExpiration(shortUrlRequest)));
            logger.info("m=buildShortUrl msg=generate-short-url alias={}", alias.get());

            redisTemplate.opsForHash().put("URL", alias.get(), shortUrlRequest.originalUrl());
            logger.info("m=buildShortUrl msg=generate-short-url cache={}", shortUrlRequest.originalUrl());

            return new ShortUrlResponse(domainApp, alias.get());
        }

    }

    @Override
    public String getShortUrl(String alias) {
        logger.info("m=getShortUrl msg=retrieving-short-url request={}", alias);
        var keyFromCache = (String) redisTemplate.opsForHash().get("URL", alias);
        if (keyFromCache != null) {
            return keyFromCache;
        }
        return urlDAO.getByAlias(alias);
    }

    private Timestamp handleTimeToExpiration(ShortUrlRequest shortUrlRequest) {
        if (Objects.isNull(shortUrlRequest.timeToExpiration())) {
            Instant instant = Instant.now().plus(Duration.ofMinutes(15));
            long timeStampMillis = instant.toEpochMilli();
            return new Timestamp(timeStampMillis);
        }

        return new Timestamp(shortUrlRequest.timeToExpiration());
    }
}

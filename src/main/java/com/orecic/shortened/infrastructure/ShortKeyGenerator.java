package com.orecic.shortened.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

public class ShortKeyGenerator {

    Logger logger = LoggerFactory.getLogger(ShortKeyGenerator.class);

    private String longUrl;

    public ShortKeyGenerator(String longUrl) {
        this.longUrl = longUrl;
    }

    public Optional<String> getKey() {
        logger.info("m=getKey msg=generating-key longUrl={}", longUrl);

        try {
            var randomSeq = UUID.randomUUID().toString();
            var unique = longUrl.concat(randomSeq);

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(unique.getBytes());
            var digest = md.digest();

            return Optional.of(Base64.getUrlEncoder().withoutPadding().encodeToString(digest).substring(0, 8));
        } catch (NoSuchAlgorithmException e) {
            logger.error("m=getKey msg=error-on-generate-key error={}", e.getMessage());
        }

        return Optional.empty();
    }
}

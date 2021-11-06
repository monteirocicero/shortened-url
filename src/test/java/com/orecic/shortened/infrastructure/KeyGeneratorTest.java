package com.orecic.shortened.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyGeneratorTest {

    @Test
    public void generateUniqueToComposeShortUrl() {
        // given
        var longUrl = "https://docs.spring.io/spring-cloud-dataflow/docs/1.1.2.RELEASE/reference/html/configuration-rdbms.html";

        // then
        var keyGenerator = new ShortKeyGenerator(longUrl);

        Assertions.assertEquals(8, keyGenerator.getKey().get().length());

    }

}

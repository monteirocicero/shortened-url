package com.orecic.shortened.infrastructure;

import java.util.UUID;

public class AliasGenerator {
    public String getAlias() {
        return UUID.randomUUID().toString();
    }
}

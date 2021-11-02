package com.orecic.shortened.domain.data;

import com.orecic.shortened.domain.entity.UrlEntity;

public interface UrlDAO {
    void save(UrlEntity shortenedUrl);
}

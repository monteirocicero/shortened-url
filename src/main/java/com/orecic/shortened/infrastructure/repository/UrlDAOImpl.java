package com.orecic.shortened.infrastructure.repository;

import com.orecic.shortened.domain.data.UrlDAO;
import com.orecic.shortened.domain.entity.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlDAOImpl implements UrlDAO {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlDAOImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void save(UrlEntity shortenedUrl) {
        urlRepository.save(shortenedUrl);
    }

    @Override
    public String getByAlias(String alias) {
        return urlRepository.findByAlias(alias);
    }
}

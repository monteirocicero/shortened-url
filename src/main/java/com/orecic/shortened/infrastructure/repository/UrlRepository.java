package com.orecic.shortened.infrastructure.repository;

import com.orecic.shortened.domain.entity.UrlEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Integer> {
    @Query(value = "SELECT original_url FROM url_entity WHERE alias = ?1 and expiration_time > current_timestamp", nativeQuery = true)
    String findByAliasAndExpirationTime(String alias);

    @Query(value = "SELECT original_url FROM url_entity WHERE alias = ?1", nativeQuery = true)
    String findByAlias(String alias);
}

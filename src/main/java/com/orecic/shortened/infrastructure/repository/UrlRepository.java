package com.orecic.shortened.infrastructure.repository;

import com.orecic.shortened.domain.entity.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Integer> {
}

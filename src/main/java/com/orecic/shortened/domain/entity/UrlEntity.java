package com.orecic.shortened.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String alias;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "expiration_time")
    private Timestamp expirationTime;

    public UrlEntity(String alias, String originalUrl, Timestamp expirationTime) {
        this.alias = alias;
        this.originalUrl = originalUrl;
        this.expirationTime = expirationTime;
    }

    public UrlEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlEntity urlEntity = (UrlEntity) o;
        return Objects.equals(id, urlEntity.id) &&
                Objects.equals(alias, urlEntity.alias) &&
                Objects.equals(originalUrl, urlEntity.originalUrl) &&
                Objects.equals(expirationTime, urlEntity.expirationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias, originalUrl, expirationTime);
    }
}

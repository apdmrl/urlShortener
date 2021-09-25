package com.tapu.urlshortener.repository;

import com.tapu.urlshortener.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl,Long> {

    ShortenedUrl getByFullUrl(String fullUrl);
}

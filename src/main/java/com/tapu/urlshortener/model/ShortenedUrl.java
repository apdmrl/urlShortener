package com.tapu.urlshortener.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ShortenedUrl {
    @Id
    public Long id;

    public String fullUrl;
    public String shortenedUrl;
}

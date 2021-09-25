package com.tapu.urlshortener.model.Dto;

import com.tapu.urlshortener.model.ShortenedUrl;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ResponseDto {
    public String status;
    public ShortenedUrl shortenedUrl;
    public List<ShortenedUrl> shortenedUrlList;
}

package com.tapu.urlshortener.model.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestDto {
    public String fullUrl;
    public String shortenedUrl;
}

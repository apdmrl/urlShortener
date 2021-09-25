package com.tapu.urlshortener.controller;

import com.tapu.urlshortener.model.Dto.RequestDto;
import com.tapu.urlshortener.model.Dto.ResponseDto;
import com.tapu.urlshortener.service.ShortenedUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping
public class UrlShortenerController {

    @Autowired
    private ShortenedUrlService shortenedUrlService;

    @PostMapping("api/create/")
    public ResponseDto createShortenedUrl(@RequestBody RequestDto requestDto){
        return shortenedUrlService.createShortenedUrl(requestDto.getFullUrl());
    }

    @GetMapping("api/getById/{id}")
    public ResponseDto getShortenedUrlById(@PathVariable Long id){
        return shortenedUrlService.getById(id);
    }

    @GetMapping("api/delete/{id}")
    public ResponseDto deleteShortenedUrlById(@PathVariable Long id){
        return shortenedUrlService.deleteShortenedUrl(id);
    }

    @GetMapping("api/getAll/")
    public ResponseDto getAllShortenedUrls(){
        return shortenedUrlService.getAllShortenedUrls();
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Void> useShorterLink(@PathVariable String shortLink){
        String fullUrl = shortenedUrlService.getFullUrl(shortLink);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://" + fullUrl)).build();
    }
}

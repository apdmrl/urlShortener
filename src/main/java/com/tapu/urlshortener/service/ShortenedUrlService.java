package com.tapu.urlshortener.service;

import com.tapu.urlshortener.common.ShorteningUrl;
import com.tapu.urlshortener.model.Dto.ResponseDto;
import com.tapu.urlshortener.model.ShortenedUrl;
import com.tapu.urlshortener.repository.ShortenedUrlRepository;
import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShortenedUrlService {


    private String host = "http://localhost:8080/";
    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;


    public ResponseDto createShortenedUrl(String fullUrl){
        ResponseDto responseDto = new ResponseDto();
        List<ShortenedUrl> shortenedUrlList = shortenedUrlRepository.findAll();
        List<String> fullUrls = shortenedUrlList.stream().map(surl -> surl.getFullUrl()).collect(Collectors.toList());
        if(fullUrls.contains(fullUrl)){
            responseDto.setStatus("This url already exist");
            ShortenedUrl existingUrl = shortenedUrlRepository.getByFullUrl(fullUrl);
            responseDto.setShortenedUrl(existingUrl);
            return responseDto;
        }else{
            ShortenedUrl shortenedUrl = new ShortenedUrl();
            shortenedUrl.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            shortenedUrl.setFullUrl(fullUrl);
            shortenedUrl.setShortenedUrl(host + ShorteningUrl.idToStr(shortenedUrl.getId()));
            shortenedUrlRepository.save(shortenedUrl);
            responseDto.setShortenedUrl(shortenedUrl);
            responseDto.setStatus(shortenedUrl + " is successfully created.");
            return responseDto;
        }
    }

    public String getFullUrl(String shortUrl){
        ShortenedUrl shortenedUrl = shortenedUrlRepository.getById(ShorteningUrl.strToId(shortUrl));
        return shortenedUrl.getFullUrl();
    }

    public ResponseDto deleteShortenedUrl(Long id){
        ResponseDto response = new ResponseDto();
        shortenedUrlRepository.deleteById(id);
        response.setStatus("successfuly deleted");
        return response;
    }

    public ResponseDto getById(Long id){
        ResponseDto response = new ResponseDto();
        response.setShortenedUrl(shortenedUrlRepository.getById(id));
        response.setStatus("successfuly");
        return response;
    }

    public ResponseDto getAllShortenedUrls(){
        ResponseDto response = new ResponseDto();
        response.setShortenedUrlList(shortenedUrlRepository.findAll());
        response.setStatus("successfully");
        return response;
    }

}

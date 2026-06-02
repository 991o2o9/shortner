package com.amin.linkforge.controller;

import com.amin.linkforge.models.UrlMapping;
import com.amin.linkforge.service.UrlMappingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlMappingService urlMappingService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);

        if (urlMapping != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlMapping.getOriginalUrl()))
                    .build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.jacobs.urlshortner.controller;

import com.jacobs.urlshortner.model.ShortUrl;
import com.jacobs.urlshortner.service.UrlShortenerService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlShortenerController {

    private final UrlShortenerService service;

    public UrlShortenerController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShortUrl> create(@RequestBody CreateUrlRequest request) {
        ShortUrl shortUrl = service.createShortUrl(request.originalUrl());
        return ResponseEntity.ok(shortUrl);
    }

    public record CreateUrlRequest(@NotBlank String originalUrl) {}
}

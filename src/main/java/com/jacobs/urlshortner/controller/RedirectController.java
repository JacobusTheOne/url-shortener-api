package com.jacobs.urlshortner.controller;

import com.jacobs.urlshortner.model.ShortUrl;
import com.jacobs.urlshortner.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    private final UrlShortenerService service;

    public RedirectController(UrlShortenerService service) {
        this.service = service;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        ShortUrl shortUrl = service.getByShortCode(shortCode);
        return ResponseEntity
                .status(302)
                .header("Location", shortUrl.getOriginalUrl())
                .build();
    }
}

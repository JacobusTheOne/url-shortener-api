package com.jacobs.urlshortner.controller;

import java.util.List;

import com.jacobs.urlshortner.model.ShortUrl;
import com.jacobs.urlshortner.service.UrlShortenerService;

import jakarta.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlShortenerController {

    private final UrlShortenerService service;

    public UrlShortenerController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShortUrl> create(@RequestBody CreateUrlRequest request, Authentication authentication) {
        String email = authentication.getName();
        ShortUrl shortUrl = service.createShortUrl(request.originalUrl(), email);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/me")
    public List<ShortUrl> myUrls(Authentication authentication) {
        String email = authentication.getName();
        return service.getUrlsForUser(email);
    }

    public record CreateUrlRequest(@NotBlank String originalUrl) {}
}

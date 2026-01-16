package com.jacobs.urlshortner.controller;

import com.jacobs.urlshortner.model.ShortUrl;
import com.jacobs.urlshortner.service.UrlShortenerService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @GetMapping("/r/{shortCode}")
public void redirect(
        @PathVariable String shortCode,
        HttpServletResponse response
) {

    ShortUrl url = service.getByShortCode(shortCode);

    try {
        response.sendRedirect(url.getOriginalUrl());
    } catch (IOException e) {
        throw new RuntimeException("Redirect failed", e);
    }
}

    
}

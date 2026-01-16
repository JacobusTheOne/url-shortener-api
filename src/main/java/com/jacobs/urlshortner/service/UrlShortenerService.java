package com.jacobs.urlshortner.service;

import com.jacobs.urlshortner.model.ShortUrl;
import com.jacobs.urlshortner.repository.ShortUrlRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortenerService {

    private final ShortUrlRepository repository;

    public UrlShortenerService(ShortUrlRepository repository) {
        this.repository = repository;
    }
    public List<ShortUrl> getUrlsForUser(String email) {
        return repository.findAllByCreatedBy(email);
    }


    public ShortUrl createShortUrl(String originalUrl) {
        String shortCode = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        ShortUrl shortUrl = new ShortUrl(originalUrl, shortCode);
        return repository.save(shortUrl);
    }

    public ShortUrl getByShortCode(String shortCode) {
    ShortUrl shortUrl = repository.findByShortCode(shortCode)
            .orElseThrow(() -> new RuntimeException("Short URL not found"));

    shortUrl.incrementClickCount();
    repository.save(shortUrl);

    return shortUrl;
}

}

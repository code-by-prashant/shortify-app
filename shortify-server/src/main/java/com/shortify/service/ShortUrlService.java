package com.shortify.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shortify.model.ShortUrl;
import com.shortify.repository.ShortUrlRepository;
import com.shortify.utils.ShortCodeGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

	@Value("${shortify.base-url}")
    private String baseUrl;
    
    public String createShortUrl(String originalUrl, String customCode) {
        String shortCode = (customCode != null && !customCode.isEmpty()) ? customCode : ShortCodeGenerator.generate();
        
        ShortUrl shortUrl = new ShortUrl(null, shortCode, originalUrl, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
        shortUrlRepository.save(shortUrl);

        return baseUrl + "/" + shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<ShortUrl> shortUrl = shortUrlRepository.findByShortCode(shortCode);
        return shortUrl.map(ShortUrl::getOriginalUrl).orElse(null);
    }
    
}

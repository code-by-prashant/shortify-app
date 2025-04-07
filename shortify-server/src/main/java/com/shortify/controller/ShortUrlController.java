package com.shortify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shortify.dto.ShortenRequest;
import com.shortify.dto.ShortenResponse;
import com.shortify.service.ShortUrlService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shortify/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "ShortUrl Controller")
public class ShortUrlController {

	private final ShortUrlService shortUrlService;

	@PostMapping("/shorten")
	public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request) {
		String shortUrl = shortUrlService.createShortUrl(request.getOriginalUrl(), request.getCustomCode());
		return ResponseEntity.ok(new ShortenResponse(shortUrl));
	}

	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
		String originalUrl = shortUrlService.getOriginalUrl(shortCode);
		return originalUrl != null ? ResponseEntity.status(302).header("Location", originalUrl).build()
				: ResponseEntity.notFound().build();
	}
}

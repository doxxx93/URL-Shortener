package me.doxxx.server.urlshortener;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/data/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request) {
        return ResponseEntity.ok(urlService.shortenUrl(request.longUrl()));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.getOriginalUrl(shortUrl)))
                .build();
    }
}

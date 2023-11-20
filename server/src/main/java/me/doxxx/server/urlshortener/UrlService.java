package me.doxxx.server.urlshortener;

import com.github.f4b6a3.ulid.Ulid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public ShortenResponse shortenUrl(String longUrl) {
        return urlRepository.findByLongUrl(longUrl)
                .map(ShortenResponse::fromUrl)
                .orElseGet(() -> createShortUrl(longUrl));
    }

    private ShortenResponse createShortUrl(String longUrl) {
        long id = Ulid.fast().getTime();
        String shortUrl = Base62Conversion.encode(id);
        Url savedUrl = urlRepository.save(Url.of(id, shortUrl, longUrl));
        return ShortenResponse.fromUrl(savedUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        Long id = Base62Conversion.decode(shortUrl);
        return urlRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid short URL: " + shortUrl)
        ).getLongUrl();
    }
}

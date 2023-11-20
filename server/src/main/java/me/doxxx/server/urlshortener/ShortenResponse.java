package me.doxxx.server.urlshortener;

public record ShortenResponse(String shortUrl) {

    public static ShortenResponse fromUrl(Url url) {
        return new ShortenResponse(url.getShortUrl());
    }
}

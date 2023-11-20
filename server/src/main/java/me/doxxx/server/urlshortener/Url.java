package me.doxxx.server.urlshortener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {

    @Id
    private Long id;
    private String shortUrl;
    @Column(unique = true)
    private String longUrl;

    public Url(Long id, String shortUrl, String longUrl) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public static Url of(Long id, String shortUrl, String longUrl) {
        return new Url(id, shortUrl, longUrl);
    }
}

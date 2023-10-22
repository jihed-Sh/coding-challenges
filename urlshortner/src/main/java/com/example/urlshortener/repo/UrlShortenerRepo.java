package com.example.urlshortener.repo;

import com.example.urlshortener.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlShortenerRepo extends MongoRepository<Url, String> {
    Url findByShortUrl(String shortUrl);

    Url findByLongUrl(String longUrl);

}

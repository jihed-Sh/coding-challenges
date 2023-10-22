package com.example.urlshortener.service;

import com.example.urlshortener.controller.UrlShortenerWS;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.repo.UrlShortenerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UrlShortenerService {
    @Autowired
    private UrlShortenerRepo urlShortenerRepo;

    public String getLongUrl(String shortUrl) {
        return urlShortenerRepo.findByShortUrl(shortUrl).getLongUrl();

    }

    public Url shortenUrl(String longUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Digest = md.digest(longUrl.getBytes());
            String base64 = Base64.getEncoder().encodeToString(md5Digest);
            String key = base64.substring(0, 6);

            Url url = new Url();
            url.setLongUrl(longUrl);
            url.setKey(key);
            url.setShortUrl("localhost:8080/" + key);
            urlShortenerRepo.save(url);
            return url; // Use the first 6 characters as the short code
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception appropriately in your code
        }
    }
}

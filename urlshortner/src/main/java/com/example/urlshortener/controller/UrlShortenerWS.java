package com.example.urlshortener.controller;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
//@RequestMapping("/api/url")

public class UrlShortenerWS {
    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerWS(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public Url shortenUrl(@RequestBody String longUrl) {
        // Validate and process the long URL, generate a short URL, and save it in the database
        return urlShortenerService.shortenUrl(longUrl);
    }

//        @GetMapping("/{shortUrl}")
//        public String redirectToLongUrl(@PathVariable String shortUrl) {
//            // Retrieve the long URL associated with the given short URL and perform a redirection
//            String longUrl = urlShortenerService.getLongUrl(shortUrl);
//            if (longUrl != null) {
//                // Perform the redirection
//                return "redirect:" + longUrl;
//            } else {
//                // Handle not found scenario
//                return "Short URL not found";
//            }
//        }

    @GetMapping("/{uniqueCode}")
    public void redirectToOriginalUrl(@PathVariable String uniqueCode, HttpServletResponse response) {
        String originalUrl = urlShortenerService.getOriginalUrl(uniqueCode);
        try {
            if (originalUrl != null) {
                response.sendRedirect(originalUrl);
            } else {
                // Handle the case where the short URL is not found (e.g., display an error message).
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

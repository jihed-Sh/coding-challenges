package com.example.urlshortener.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Url {

    private String id;
    private String shortUrl;
    private String key;
    private String longUrl;

}

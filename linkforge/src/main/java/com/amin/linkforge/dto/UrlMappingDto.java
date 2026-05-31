package com.amin.linkforge.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private String username;
    private int clickCount;
    private LocalDateTime createdDate;
}

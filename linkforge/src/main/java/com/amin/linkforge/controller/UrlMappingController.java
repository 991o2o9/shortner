package com.amin.linkforge.controller;

import com.amin.linkforge.dto.UrlMappingDto;
import com.amin.linkforge.models.User;
import com.amin.linkforge.repository.UserRepository;
import com.amin.linkforge.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserRepository userRepository;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDto> createShortUrl(
            @RequestBody Map<String, String> request,
            Principal principal){
        String originalUrl = request.get("originalUrl");
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + principal.getName()));
        UrlMappingDto urlMappingDto = urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDto);
    }
}

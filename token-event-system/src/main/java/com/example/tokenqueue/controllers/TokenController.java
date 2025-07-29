package com.example.tokenqueue.controllers;

import com.example.tokenqueue.models.TokenModel;
import com.example.tokenqueue.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/{departmentId}")
    public TokenModel createToken(@PathVariable String departmentId, @RequestParam Long userId) {
        return tokenService.createToken(departmentId, userId);
    }

    @GetMapping("/status/{userId}")
    public TokenModel getTokenStatus(@PathVariable Long userId) {
        return tokenService.getTokenByUser(userId);
    }

    @PutMapping("/{tokenId}")
    public TokenModel updateStatus(@PathVariable Long tokenId, @RequestParam String status) {
        return tokenService.updateStatus(tokenId, status);
    }
}

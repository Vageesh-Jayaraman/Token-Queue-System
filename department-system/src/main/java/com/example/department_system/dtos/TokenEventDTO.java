package com.example.department_system.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenEventDTO {
    private String eventType;
    private Long tokenId;
}

package com.example.tokenqueue.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenEventDTO {
    private String eventType;
    private Long tokenId;
    private String departmentId;
    private Long tokenNo;
    private Long userId;
    private String status;
    private String timestamp;
}

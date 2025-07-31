package com.example.consumer_notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEventDTO {
    private String eventType;
    private Long tokenId;
    private String departmentId;
    private Long tokenNo;
    private Long userId;
    private String status;
    private String timestamp;
}

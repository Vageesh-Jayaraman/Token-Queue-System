package com.example.consumer_display.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer_display.services.SSEService;

import reactor.core.publisher.Flux;

@RestController

public class StreamController {

    @Autowired
    SSEService sseService;
    
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> getEvents(){
        return sseService.getEvents();
    }
}

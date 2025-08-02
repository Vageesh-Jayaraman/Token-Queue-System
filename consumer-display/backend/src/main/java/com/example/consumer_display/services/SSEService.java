package com.example.consumer_display.services;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class SSEService {

    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void send(String data) {
        sink.tryEmitNext(data);
    }

    public Flux<ServerSentEvent<String>> getEvents(){
        return sink.asFlux().map(data ->
            ServerSentEvent.<String>builder()
                .data(data)
                .build()
        );
    }
    
}

package com.springAi.LamNexus.DTO;

public record ChatResponse(String provider, String model, String response, long latency) {
}

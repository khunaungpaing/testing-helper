package com.khun.testinghelper.domain.dto;

public record ErrorResponse (
    int status,
    String error,
    String message
){}
package com.example.atmConsole.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;


@Builder
public class ResponseDto<T> {


    @JsonProperty("status")
    private String status;


    @JsonProperty("message")
    private T message;


    public ResponseDto() {
    }

    public ResponseDto(String status, T message) {
        this.status = status;
        this.message = message;

    }
}

package com.example.model;


import lombok.Data;

import java.util.List;

@Data
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

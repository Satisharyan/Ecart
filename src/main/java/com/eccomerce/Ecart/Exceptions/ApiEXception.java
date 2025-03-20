package com.eccomerce.Ecart.Exceptions;

public class ApiEXception extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApiEXception() {
    }

    public ApiEXception(String message) {
        super(message);
    }
}

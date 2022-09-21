package com.home.langsd.calculator.exception;

public class ParsingException extends RuntimeException {

    public ParsingException() {
        super("Invalid expression");
    }
}

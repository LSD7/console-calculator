package com.home.langsd.calculator.exception;

public class OperationException extends RuntimeException {
    public OperationException() {
        super("Can't calculate the expression. Check the expression");
    }
}

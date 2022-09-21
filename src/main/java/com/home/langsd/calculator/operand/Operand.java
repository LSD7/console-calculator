package com.home.langsd.calculator.operand;


import com.home.langsd.calculator.exception.OperationException;

public interface Operand {
    default double compute(Double a, Double b) {
        throw new OperationException();
    }

    int getPriority();

    String getSymbol();
}

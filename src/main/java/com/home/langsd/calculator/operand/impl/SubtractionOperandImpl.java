package com.home.langsd.calculator.operand.impl;

import com.home.langsd.calculator.operand.Operand;
import org.springframework.stereotype.Component;

import static com.home.langsd.calculator.config.Constant.FIRST_LEVEL_ARITHMETIC_PRIORITY;
import static com.home.langsd.calculator.config.Constant.MINUS_SYMBOL;

@Component
public class SubtractionOperandImpl implements Operand {
    @Override
    public double compute(Double a, Double b) {
        return a - b;
    }

    @Override
    public int getPriority() {
        return FIRST_LEVEL_ARITHMETIC_PRIORITY;
    }

    @Override
    public String getSymbol() {
        return MINUS_SYMBOL;
    }
}

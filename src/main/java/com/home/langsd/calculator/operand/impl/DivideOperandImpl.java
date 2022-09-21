package com.home.langsd.calculator.operand.impl;

import com.home.langsd.calculator.operand.Operand;
import org.springframework.stereotype.Component;

import static com.home.langsd.calculator.config.Constant.DIVIDE_SYMBOL;
import static com.home.langsd.calculator.config.Constant.SECOND_LEVEL_ARITHMETIC_PRIORITY;

@Component
public class DivideOperandImpl implements Operand {
    @Override
    public double compute(Double a, Double b) {
        return a / b;
    }

    @Override
    public int getPriority() {
        return SECOND_LEVEL_ARITHMETIC_PRIORITY;
    }

    @Override
    public String getSymbol() {
        return DIVIDE_SYMBOL;
    }
}

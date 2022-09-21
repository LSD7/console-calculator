package com.home.langsd.calculator.operand.impl;

import com.home.langsd.calculator.operand.Operand;
import org.springframework.stereotype.Component;

import static com.home.langsd.calculator.config.Constant.LEFT_BRACKET;
import static com.home.langsd.calculator.config.Constant.NON_ARITHMETIC_PRIORITY;

@Component
public class LeftBracketOperandImpl implements Operand {
    @Override
    public int getPriority() {
        return NON_ARITHMETIC_PRIORITY;
    }

    @Override
    public String getSymbol() {
        return LEFT_BRACKET;
    }
}

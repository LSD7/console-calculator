package com.home.langsd.calculator.operand.impl;

import com.home.langsd.calculator.operand.Operand;
import org.springframework.stereotype.Component;

import static com.home.langsd.calculator.config.Constant.NON_ARITHMETIC_PRIORITY;
import static com.home.langsd.calculator.config.Constant.RIGHT_BRACKET;

@Component
public class RightBracketOperandImpl implements Operand {
    @Override
    public int getPriority() {
        return NON_ARITHMETIC_PRIORITY;
    }

    @Override
    public String getSymbol() {
        return RIGHT_BRACKET;
    }
}

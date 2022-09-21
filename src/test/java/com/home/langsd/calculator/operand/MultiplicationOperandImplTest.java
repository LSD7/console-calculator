package com.home.langsd.calculator.operand;

import com.home.langsd.calculator.exception.OperationException;
import com.home.langsd.calculator.operand.impl.MultiplicationOperandImpl;
import com.home.langsd.calculator.operand.impl.RightBracketOperandImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.home.langsd.calculator.config.Constant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class MultiplicationOperandImplTest {
    private final MultiplicationOperandImpl multiplicationOperand = new MultiplicationOperandImpl();

    @Test
    public void testPriority() {
        assertThat(multiplicationOperand.getPriority()).isEqualTo(SECOND_LEVEL_ARITHMETIC_PRIORITY);
    }

    @Test
    public void testSymbol() {
        assertThat(multiplicationOperand.getSymbol()).isEqualTo(MULTIPLICATION_SYMBOL);
    }

    @Test
    public void testCompute() {
        assertThat(multiplicationOperand.compute(1.0, 2.0)).isEqualTo(2.0);
    }
}

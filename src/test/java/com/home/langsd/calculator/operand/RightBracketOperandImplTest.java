package com.home.langsd.calculator.operand;

import com.home.langsd.calculator.exception.OperationException;
import com.home.langsd.calculator.operand.impl.RightBracketOperandImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.home.langsd.calculator.config.Constant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class RightBracketOperandImplTest {
    private final RightBracketOperandImpl rightBracketOperand = new RightBracketOperandImpl();

    @Test
    public void testPriority() {
        assertThat(rightBracketOperand.getPriority()).isEqualTo(NON_ARITHMETIC_PRIORITY);
    }

    @Test
    public void testSymbol() {
        assertThat(rightBracketOperand.getSymbol()).isEqualTo(RIGHT_BRACKET);
    }

    @Test
    public void testCompute() {
        assertThrows(OperationException.class, () -> rightBracketOperand.compute(1.1, 2.0));
    }
}

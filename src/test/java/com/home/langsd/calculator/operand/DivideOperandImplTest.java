package com.home.langsd.calculator.operand;

import com.home.langsd.calculator.operand.impl.DivideOperandImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.home.langsd.calculator.config.Constant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DivideOperandImplTest {
    private final DivideOperandImpl divideOperand = new DivideOperandImpl();

    @Test
    public void testPriority() {
        assertThat(divideOperand.getPriority()).isEqualTo(SECOND_LEVEL_ARITHMETIC_PRIORITY);
    }

    @Test
    public void testSymbol() {
        assertThat(divideOperand.getSymbol()).isEqualTo(DIVIDE_SYMBOL);
    }

    @Test
    public void testCompute() {
        assertThat(divideOperand.compute(4.0, 2.0)).isEqualTo(2.0);
    }
}

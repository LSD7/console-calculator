package com.home.langsd.calculator.operand;

import com.home.langsd.calculator.operand.impl.SubtractionOperandImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.home.langsd.calculator.config.Constant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SubtractionOperandImplTest {
    private final SubtractionOperandImpl subtractionOperand = new SubtractionOperandImpl();

    @Test
    public void testPriority() {
        assertThat(subtractionOperand.getPriority()).isEqualTo(FIRST_LEVEL_ARITHMETIC_PRIORITY);
    }

    @Test
    public void testSymbol() {
        assertThat(subtractionOperand.getSymbol()).isEqualTo(MINUS_SYMBOL);
    }

    @Test
    public void testCompute() {
        assertThat(subtractionOperand.compute(4.0, 2.0)).isEqualTo(2.0);
    }
}

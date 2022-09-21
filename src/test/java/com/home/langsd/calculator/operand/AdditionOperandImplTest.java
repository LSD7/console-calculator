package com.home.langsd.calculator.operand;

import com.home.langsd.calculator.operand.impl.AdditionOperandImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.home.langsd.calculator.config.Constant.FIRST_LEVEL_ARITHMETIC_PRIORITY;
import static com.home.langsd.calculator.config.Constant.PLUS_SYMBOL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdditionOperandImplTest {
    private final AdditionOperandImpl additionOperand = new AdditionOperandImpl();

    @Test
    public void testPriority() {
        assertThat(additionOperand.getPriority()).isEqualTo(FIRST_LEVEL_ARITHMETIC_PRIORITY);
    }

    @Test
    public void testSymbol() {
        assertThat(additionOperand.getSymbol()).isEqualTo(PLUS_SYMBOL);
    }

    @Test
    public void testCompute() {
        assertThat(additionOperand.compute(1.0, 2.0)).isEqualTo(3.0);
    }
}

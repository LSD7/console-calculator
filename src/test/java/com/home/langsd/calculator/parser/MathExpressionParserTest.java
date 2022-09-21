package com.home.langsd.calculator.parser;

import com.home.langsd.calculator.exception.OperationException;
import com.home.langsd.calculator.exception.ParsingException;
import com.home.langsd.calculator.operand.Operand;
import com.home.langsd.calculator.operand.impl.AdditionOperandImpl;
import com.home.langsd.calculator.operand.impl.LeftBracketOperandImpl;
import com.home.langsd.calculator.operand.impl.DivideOperandImpl;
import com.home.langsd.calculator.operand.impl.MultiplicationOperandImpl;
import com.home.langsd.calculator.operand.impl.RightBracketOperandImpl;
import com.home.langsd.calculator.operand.impl.SubtractionOperandImpl;
import com.home.langsd.calculator.parser.impl.MathExpressionParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class MathExpressionParserTest {
    private final List<Operand> operandList = List.of(new AdditionOperandImpl(), new LeftBracketOperandImpl(),
            new RightBracketOperandImpl(), new DivideOperandImpl(), new MultiplicationOperandImpl(), new SubtractionOperandImpl());

    private final MathExpressionParser expressionParser = new MathExpressionParser(operandList);

    @Test
    public void testAdditionOperation() {
        Double result = expressionParser.parseExpression("1+1");

        assertThat(result).isEqualTo(2.0);
    }

    @Test
    public void testAdditionOperationLongValues() {
        Double result = expressionParser.parseExpression("10000+10001");

        assertThat(result).isEqualTo(20001.0);
    }

    @Test
    public void testSubtractionOperation() {
        Double result = expressionParser.parseExpression("5-4");

        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void testMultiplicationOperation() {
        Double result = expressionParser.parseExpression("5*4");

        assertThat(result).isEqualTo(20.0);
    }

    @Test
    public void testDivideOperation() {
        Double result = expressionParser.parseExpression("10/5");

        assertThat(result).isEqualTo(2.0);
    }

    @Test
    public void testDivideOperationWithRemainder() {
        Double result = expressionParser.parseExpression("10/3");

        assertThat(result).isEqualTo(3.3333333333333335);
    }

    @Test
    public void testOperationWithBracketsAndMultiple() {
        Double result = expressionParser.parseExpression("1+1+(10*5)*2-5");

        assertThat(result).isEqualTo(97.0);
    }

    @Test
    public void testOperationWithBracketsAtStart() {
        Double result = expressionParser.parseExpression("(1+9)*5*2-5");

        assertThat(result).isEqualTo(95.0);
    }

    @Test
    public void testUnarySubtractionOperation() {
        Double result = expressionParser.parseExpression("-5-4");

        assertThat(result).isEqualTo(-9.0);
    }

    @Test
    public void testPositiveUnaryAdditionalOperation() {
        Double result = expressionParser.parseExpression("-5+6");

        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void testNegativeUnaryAdditionalOperation() {
        Double result = expressionParser.parseExpression("-5+4");

        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void testUnaryMultipleOperation() {
        Double result = expressionParser.parseExpression("-5*4");

        assertThat(result).isEqualTo(-20.0);
    }

    @Test
    public void testUnaryDivideOperation() {
        Double result = expressionParser.parseExpression("-20/5");

        assertThat(result).isEqualTo(-4.0);
    }

    @Test
    public void testUnaryMultipleWithBrackets() {
        Double result = expressionParser.parseExpression("2*(-3)");

        assertThat(result).isEqualTo(-6.0);
    }

    @Test
    public void testUnarySubtractionWithBrackets() {
        Double result = expressionParser.parseExpression("3-(-3)");

        assertThat(result).isEqualTo(6.0);
    }

    @Test
    public void testMultiplyOperations() {
        Double result = expressionParser.parseExpression("1-1+5+10*5/5+(10+10)+10*(10*2)");

        assertThat(result).isEqualTo(235.0);
    }

    @Test
    public void testWhitespacesIgnore() {
        Double result = expressionParser.parseExpression("1 +             1");

        assertThat(result).isEqualTo(2.0);
    }

    @Test
    public void testUnknownOperator() {
        assertThrows(ParsingException.class, () -> expressionParser.parseExpression("4||2"));
    }

    @Test
    public void testExceptionWithAlphabet() {
        assertThrows(ParsingException.class, () -> expressionParser.parseExpression("asd"));
    }

    @Test
    public void testException() {
        assertThrows(OperationException.class, () -> expressionParser.parseExpression("1+1+"));
    }

}

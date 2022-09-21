package com.home.langsd.calculator.parser.impl;

import com.home.langsd.calculator.exception.OperationException;
import com.home.langsd.calculator.exception.ParsingException;
import com.home.langsd.calculator.operand.Operand;
import com.home.langsd.calculator.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.home.langsd.calculator.config.Constant.*;


/**
 * This class represents logic for processing math expressions
 */
@Service
public class MathExpressionParser implements Parser {
    private final List<Operand> operands;
    private final StringBuilder stringBuilder;
    private final LinkedList<Double> operandsStack;
    private final LinkedList<Operand> operatorsStack;

    @Autowired
    public MathExpressionParser(List<Operand> operands) {
        this.operands = operands;
        this.stringBuilder = new StringBuilder();
        this.operandsStack = new LinkedList<>();
        this.operatorsStack = new LinkedList<>();
    }

    /**
     * This method processes math expressions. For support of new operations you should implement {@code Operation}
     *
     * @param expression for parsing
     * @return mathematical result of expression
     * @throws Exception if can't processes expression
     */
    public double parseExpression(String expression) throws ParsingException {
        if (expression.matches(ALPHABET_REGEX)) {
            throw new ParsingException();
        }
        for (int i = 0; i < expression.length(); i++) {
            String elem = String.valueOf(expression.charAt(i));
            Optional<Operand> operation = operands.stream()
                    .filter(itemOperation -> itemOperation.getSymbol().equals(elem))
                    .findFirst();
            if (elem.isBlank()) {
                continue;
            }

            if (elem.equals(LEFT_BRACKET) && operation.isPresent()) {
                operatorsStack.add(operation.get());
            } else if (elem.equals(RIGHT_BRACKET) && operation.isPresent()) {
                processBracketMathOperations();
            } else if (isMathOperation(operation, i) && !isUnaryOperatorAfterBracket(elem, operatorsStack)) {
                processMathOperation(operation);
            } else {
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || isUnaryOperator(expression, i))) {
                    stringBuilder.append(expression.charAt(i++));
                }
                --i;
                processNumeric();
            }
        }
        while (!operatorsStack.isEmpty()) {
            processOperator(operandsStack, operatorsStack.removeLast());
        }

        return operandsStack.removeFirst();
    }

    private void processNumeric() {
        if (stringBuilder.length() == 0) {
            throw new ParsingException();
        }
        operandsStack.add(Double.parseDouble(stringBuilder.toString()));
        stringBuilder.setLength(0);
    }

    private void processBracketMathOperations() {
        while (!operatorsStack.getLast().getSymbol().equals(LEFT_BRACKET)) {
            processOperator(operandsStack, operatorsStack.removeLast());
        }
        operatorsStack.removeLast();
    }

    private void processMathOperation(Optional<Operand> operation) {
        while (!operatorsStack.isEmpty() && isLastOperatorHigher(operatorsStack.getLast(), operation.get())) {
            processOperator(operandsStack, operatorsStack.removeLast());
        }
        operatorsStack.add(operation.get());
    }

    private boolean isMathOperation(Optional<Operand> operation, int i) {
        return operation.isPresent() && i != 0;
    }

    private boolean isUnaryOperatorAfterBracket(String elem, LinkedList<Operand> operatorsStack) {
        return (elem.equals(MINUS_SYMBOL) && !operatorsStack.isEmpty() && operatorsStack.peekLast().getSymbol().equals(LEFT_BRACKET));
    }

    private boolean isLastOperatorHigher(Operand lastOperator, Operand currentOperand) {
        return lastOperator.getPriority() >= currentOperand.getPriority();
    }

    private boolean isUnaryOperator(String expression, int currentIndex) {
        return expression.charAt(currentIndex) == UNARY_OPERATOR && stringBuilder.length() == 0;
    }

    private void processOperator(LinkedList<Double> operandsStack, Operand operand) {
        if (operandsStack.size() < MIN_OPERANDS_FOR_OPERATION) {
            throw new OperationException();
        }
        double rightValue = operandsStack.removeLast();
        double leftValue = operandsStack.removeLast();
        operandsStack.add(operand.compute(leftValue, rightValue));
    }
}

package com.home.langsd.calculator.parser;

import com.home.langsd.calculator.exception.ParsingException;

public interface Parser {
    double parseExpression(String s) throws ParsingException;
}

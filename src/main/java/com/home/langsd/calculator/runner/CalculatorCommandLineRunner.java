package com.home.langsd.calculator.runner;

import com.home.langsd.calculator.operand.Operand;
import com.home.langsd.calculator.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static com.home.langsd.calculator.config.Constant.STOP_EXECUTION_WORD;


/**
 * Entrypoint for CLR spring boot application
 */
@Service
public class CalculatorCommandLineRunner implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(CalculatorCommandLineRunner.class);

    private final Parser mathExpressionParser;
    private final List<Operand> operandList;

    @Autowired
    public CalculatorCommandLineRunner(Parser mathExpressionParser, List<Operand> operandList) {
        this.mathExpressionParser = mathExpressionParser;
        this.operandList = operandList;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Enter expression. Possible operators: {}", operandList.stream().map(Operand::getSymbol).collect(Collectors.toList()));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = Strings.EMPTY;
            while (!STOP_EXECUTION_WORD.equalsIgnoreCase(line)) {
                line = reader.readLine();
                LOGGER.info("Result {}", mathExpressionParser.parseExpression(line));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}



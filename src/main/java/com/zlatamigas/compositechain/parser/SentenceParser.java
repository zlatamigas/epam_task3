package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.SimpleTextComponent;
import com.zlatamigas.compositechain.interpreter.CalculateArithmeticExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParserHandler {

    public static final String SENTENCE_DELIMITER = " ";
    public static final String LEXEME_PARTS_REGEX =
            "([a-zA-Z]+(-[a-zA-Z]+)*)|([-!?.,':()]+(?=$|[a-zA-Z]))|(^-?[\\d()]+(?=[^a-zA-Z])[-/\\d()+*]*)";

    public SentenceParser() {
        super(new WordParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        ComplexTextComponent sentence = (ComplexTextComponent) component;

        ComplexTextComponent word;
        SimpleTextComponent simpleTextValue;

        Pattern patternLexeme = Pattern.compile(LEXEME_PARTS_REGEX);
        Matcher matcher;

        String[] wordStrs = strToParse.split(SENTENCE_DELIMITER);
        for (String str : wordStrs) {

            matcher = patternLexeme.matcher(str);
            while (matcher.find()) {
                if (matcher.group(1) != null) {

                    str = matcher.group(1);
                    word = new ComplexTextComponent(TextComponentType.WORD);
                    successor.handleParse(word, str);
                    sentence.addComponent(word);

                } else if (matcher.group(3) != null) {

                    str = matcher.group(3);
                    simpleTextValue = new SimpleTextComponent(TextComponentType.PUNCTUATION_MARK, str);
                    sentence.addComponent(simpleTextValue);

                } else if (matcher.group(4) != null) {

                    str = matcher.group(4);
                    CalculateArithmeticExpression arithmeticExpressionCounter = new CalculateArithmeticExpression(str);
                    double value = arithmeticExpressionCounter.calculate();
                    simpleTextValue = new SimpleTextComponent(TextComponentType.ARITHMETIC_EXCEPTION_VALUE, Double.toString(value));
                    sentence.addComponent(simpleTextValue);
                }
            }
        }
    }
}

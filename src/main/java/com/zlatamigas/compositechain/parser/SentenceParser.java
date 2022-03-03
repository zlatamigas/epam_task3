package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.ComplexTextComponentType;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.impl.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SentenceParser extends AbstractParserHandler {

    private static final ComplexTextComponentType COMPONENT_TYPE = ComplexTextComponentType.SENTENCE;

    public static final String SENTENCE_DELIMITER = " ";
    public static final String LEXEME_PARTS_REGEX = "(\\w+-?\\w*)|(\\p{Punct}+$)|(-?[\\d(]+[\\d()-+/\\*]*)";

    public SentenceParser() {
        super(new WordParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        ComplexTextComponent sentence = (ComplexTextComponent) component;

        ComplexTextComponent word;
        PunctuationMark punctuationMark;
        ArithmeticExprValue arithmeticExprValue;

        Pattern patternLexeme = Pattern.compile(LEXEME_PARTS_REGEX);
        Matcher matcher;

        String[] wordStrs = strToParse.split(SENTENCE_DELIMITER);
        for (String str : wordStrs) {


            matcher = patternLexeme.matcher(str);
            while (matcher.find()) {
                if (matcher.group(1) != null) {

                    str = matcher.group(1);
                    word = new ComplexTextComponent(COMPONENT_TYPE);
                    successor.handleParse(word, str);
                    sentence.addComponent(sentence);

                } else if (matcher.group(2) != null) {

                    str = matcher.group(2);
                    punctuationMark = new PunctuationMark(str);
                    sentence.addComponent(punctuationMark);

                } else if (matcher.group(3) != null) {

                    str = matcher.group(3);
                    //converter!!
                    //arithmeticExprValue = new ArithmeticExprValue(Double.parseDouble(str));
                    //sentence.addComponent(arithmeticExprValue);
                }
            }
        }
    }
}

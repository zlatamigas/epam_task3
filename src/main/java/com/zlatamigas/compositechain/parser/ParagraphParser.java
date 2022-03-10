package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParserHandler {

    public static final String SENTENCE_REGEX = "([^.!?]+[.!?])";

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        ComplexTextComponent paragraph = (ComplexTextComponent) component;
        ComplexTextComponent sentence;
        String str;

        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(strToParse);
        while (matcher.find()) {
            str = matcher.group(1);
            if (str != null) {

                sentence = new ComplexTextComponent(TextComponentType.SENTENCE);
                successor.handleParse(sentence, str);
                paragraph.addComponent(sentence);
            }
        }
    }
}

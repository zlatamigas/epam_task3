package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParserHandler {

    private static final Logger logger = LogManager.getLogger();

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
        logger.debug("Start parsing paragraph");
        while (matcher.find()) {
            str = matcher.group(1);
            if (str != null) {

                sentence = new ComplexTextComponent(TextComponentType.SENTENCE);
                successor.handleParse(sentence, str);
                paragraph.addComponent(sentence);
            }
        }
        logger.debug("Finish parsing paragraph");
    }
}

package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.impl.ComplexTextComponent;

public class TextParser extends AbstractParserHandler {

    private static final String PARAGRAPH_DELIMITER = "\\t";

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {

        ComplexTextComponent text = (ComplexTextComponent) component;
        ComplexTextComponent paragraph;

        String[] paragraphStrs = strToParse.trim().split(PARAGRAPH_DELIMITER);

        for (String paragraphStr : paragraphStrs) {

            paragraph = new ComplexTextComponent(TextComponentType.PARAGRAPH);
            successor.handleParse(paragraph, paragraphStr.strip());
            text.addComponent(paragraph);
        }
    }
}

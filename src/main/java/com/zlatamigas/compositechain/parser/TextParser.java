package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.impl.ComplexTextComponent;

public class TextParser extends AbstractParserHandler {

    private static final TextComponentType COMPONENT_TYPE = TextComponentType.PARAGRAPH;

    private static final String PARAGRAPH_DELIMITER = " {4}";

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {

        ComplexTextComponent text = (ComplexTextComponent) component;
        ComplexTextComponent paragraph;

        String[] paragraphStrs = strToParse.split(PARAGRAPH_DELIMITER);

        if (paragraphStrs.length < 2) {
            return;
        }


        for (int i = 1; i < paragraphStrs.length; i++) {

            paragraph = new ComplexTextComponent(COMPONENT_TYPE);
            successor.handleParse(paragraph, paragraphStrs[i].strip());
            text.addComponent(paragraph);
        }
    }
}

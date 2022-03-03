package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.ComplexTextComponentType;
import com.zlatamigas.compositechain.entity.TextComponent;

public class TextParser extends AbstractParserHandler {

    private static final ComplexTextComponentType COMPONENT_TYPE = ComplexTextComponentType.TEXT;

    private static final String PARAGRAPH_DELIMITER = " {6}";

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {

        ComplexTextComponent text = (ComplexTextComponent) component;
        ComplexTextComponent paragraph;

        String[] paragraphStrs = strToParse.split(PARAGRAPH_DELIMITER);

        for (String str : paragraphStrs) {

            if (str.isBlank()) {
                continue;
            }

            paragraph = new ComplexTextComponent(COMPONENT_TYPE);
            successor.handleParse(paragraph, str.strip());
            text.addComponent(paragraph);
        }
    }
}

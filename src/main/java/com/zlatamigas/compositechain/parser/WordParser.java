package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.impl.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.impl.SimpleTextComponent;

public class WordParser extends AbstractParserHandler {

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        ComplexTextComponent word = (ComplexTextComponent) component;

        for (char symbol : strToParse.toCharArray()) {
            word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, Character.toString(symbol)));
        }
    }
}

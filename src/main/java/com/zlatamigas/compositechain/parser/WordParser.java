package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.ComplexTextComponentType;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.impl.Symbol;

public class WordParser extends AbstractParserHandler {

    private static final ComplexTextComponentType COMPONENT_TYPE = ComplexTextComponentType.WORD;

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        ComplexTextComponent word = (ComplexTextComponent) component;

        for(char symbol : strToParse.toCharArray()){
            word.addComponent(new Symbol(symbol));
        }
    }
}

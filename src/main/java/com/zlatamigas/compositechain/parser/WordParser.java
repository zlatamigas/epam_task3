package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.impl.Symbol;
import com.zlatamigas.compositechain.entity.impl.Word;

public class WordParser extends AbstractParserHandler {

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        Word word = (Word) component;

        for(char symbol : strToParse.toCharArray()){
            word.addComponent(new Symbol(symbol));
        }
    }
}

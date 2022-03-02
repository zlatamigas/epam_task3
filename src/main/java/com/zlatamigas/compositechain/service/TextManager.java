package com.zlatamigas.compositechain.service;

import com.zlatamigas.compositechain.entity.impl.CustomText;
import com.zlatamigas.compositechain.parser.TextParser;

public class TextManager {
    public static CustomText parseText(String textStr) {
        CustomText text = new CustomText();

        if(textStr == null){
            return text;
        }

        TextParser parser = new TextParser();
        parser.handleParse(text, textStr);

        return text;
    }
}

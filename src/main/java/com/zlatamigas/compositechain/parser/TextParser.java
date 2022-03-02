package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.impl.Paragraph;
import com.zlatamigas.compositechain.entity.impl.CustomText;

public class TextParser extends AbstractParserHandler{

    private static final String PARAGRAPH_DELIMITER = " {6}";

    public TextParser(){
        super(new ParagraphParser());
    }

    @Override
    public void handleParse(TextComponent component, String strToParse) {
        CustomText text = (CustomText) component;
        Paragraph paragraph;

        String[] paragraphStrs = strToParse.split(PARAGRAPH_DELIMITER);

        for(String str : paragraphStrs){

            if(str.isBlank()){
                continue;
            }

            paragraph = new Paragraph();
            successor.handleParse(paragraph, str.strip());
            text.addComponent(paragraph);
        }
    }
}

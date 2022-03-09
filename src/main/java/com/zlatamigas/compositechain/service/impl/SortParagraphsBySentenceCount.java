package com.zlatamigas.compositechain.service.impl;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.impl.ComplexTextComponent;
import com.zlatamigas.compositechain.exceptions.CustomTextException;
import com.zlatamigas.compositechain.service.CustomTextSorter;
import com.zlatamigas.compositechain.service.comparator.InnerComponentCountComparator;

import java.util.Comparator;

public class SortParagraphsBySentenceCount implements CustomTextSorter {

    private InnerComponentCountComparator comparator = new InnerComponentCountComparator();

    @Override
    public void sort(TextComponent component) throws CustomTextException {
        if(!(component instanceof ComplexTextComponent)){
            throw new CustomTextException("Invalid TextComponent type: "
                    + component.getClass().getName() + ". Require ComplexTextComponent.");
        }

        ComplexTextComponent text = (ComplexTextComponent) component;
        if(text.getType()!= TextComponentType.TEXT){
            throw new CustomTextException("Invalid ComplexTextComponent type: "
                    + text.getType() + ". Require TEXT type.");
        }

        text.getComponents().sort(comparator);
    }
}

package com.zlatamigas.compositechain.service.impl;

import com.zlatamigas.compositechain.comparator.ComponentSizeComparator;
import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.service.CustomTextSorter;

public class SortTextParagraphsBySize implements CustomTextSorter {

    private static final ComponentSizeComparator comparator = new ComponentSizeComparator();

    @Override
    public void sort(TextComponent component) throws CustomTextException {
        if (!(component instanceof ComplexTextComponent)) {
            throw new CustomTextException("Invalid TextComponent type: "
                    + component.getClass().getName() + ". Require ComplexTextComponent.");
        }

        ComplexTextComponent text = (ComplexTextComponent) component;
        if (text.getType() != TextComponentType.TEXT) {
            throw new CustomTextException("Invalid ComplexTextComponent type: "
                    + text.getType() + ". Require TEXT type.");
        }

        text.getComponents().sort(comparator);
    }
}

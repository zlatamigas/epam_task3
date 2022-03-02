package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.AbstractComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.List;

public class Paragraph extends AbstractComplexTextComponent {

    public Paragraph() {
        super();
    }

    public Paragraph(List<TextComponent> components) {
        super(components);
    }
}

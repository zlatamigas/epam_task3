package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.AbstractComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.List;

public class Word extends AbstractComplexTextComponent {

    public Word() {
        super();
    }

    public Word(List<TextComponent> components) {
        super(components);
    }
}

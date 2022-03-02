package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.AbstractComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.List;

public class Sentence extends AbstractComplexTextComponent {

    public Sentence() {
        super();
    }

    public Sentence(List<TextComponent> components) {
        super(components);
    }
}

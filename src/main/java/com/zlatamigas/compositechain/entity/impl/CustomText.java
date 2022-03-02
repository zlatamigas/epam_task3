package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.AbstractComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.List;

public class CustomText extends AbstractComplexTextComponent {

    public CustomText(){
        super();
    }

    public CustomText(List<TextComponent> components){
        super(components);
    }
}

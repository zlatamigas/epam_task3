package com.zlatamigas.compositechain.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractComplexTextComponent implements TextComponent {

    private List<TextComponent> components;

    public AbstractComplexTextComponent(){
        components = new ArrayList<>();
    }

    public AbstractComplexTextComponent(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public boolean addComponent(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public Iterator<TextComponent> iterator() {
        return components.iterator();
    }
}

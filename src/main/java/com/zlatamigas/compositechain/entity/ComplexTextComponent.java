package com.zlatamigas.compositechain.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexTextComponent implements TextComponent {

    private List<TextComponent> components;
    private ComplexTextComponentType type;

    public ComplexTextComponent(ComplexTextComponentType type){
        this.type = type;
        components = new ArrayList<>();
    }

    public ComplexTextComponentType getType() {
        return type;
    }

    public void setType(ComplexTextComponentType type) {
        this.type = type;
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

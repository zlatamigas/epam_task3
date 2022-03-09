package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ComplexTextComponent implements TextComponent {

    private List<TextComponent> components;
    private TextComponentType type;

    public ComplexTextComponent(TextComponentType type) {
        this.type = type;
        components = new ArrayList<>();
    }

    public TextComponentType getType() {
        return type;
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public int size(){
        return components.size();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplexTextComponent that = (ComplexTextComponent) o;
        return components.equals(that.components) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(components, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(type.getPrefix());
        for(TextComponent textComponent : components){
            sb.append(textComponent);
        }
        sb.append(type.getPostfix());
        return sb.toString();
    }
}

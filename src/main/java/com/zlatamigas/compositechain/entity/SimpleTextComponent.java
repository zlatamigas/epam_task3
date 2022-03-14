package com.zlatamigas.compositechain.entity;

import java.util.Iterator;

public class SimpleTextComponent implements TextComponent {

    private String simpleTextComponent;
    private TextComponentType type;

    public SimpleTextComponent(TextComponentType type, String simpleTextComponent) {
        this.type = type;
        this.simpleTextComponent = simpleTextComponent;
    }

    public String getSimpleTextComponent() {
        return simpleTextComponent;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean addComponent(TextComponent component) {
        throw new UnsupportedOperationException("Simple text component (" + this.getClass().getName() + ") cannot contain subcomponents.");
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        throw new UnsupportedOperationException("Simple text component (" + this.getClass().getName() + ") cannot remove subcomponents.");
    }

    @Override
    public Iterator<TextComponent> iterator() {
        throw new UnsupportedOperationException("Simple text component (" + this.getClass().getName() + ") cannot iterate over not existing subcomponents.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleTextComponent that = (SimpleTextComponent) o;
        return simpleTextComponent.equals(that.simpleTextComponent) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + simpleTextComponent.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(type.getPrefix());
        sb.append(simpleTextComponent);
        sb.append(type.getPostfix());
        return sb.toString();
    }
}

package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.Iterator;

public class ArithmeticExprValue implements TextComponent {

    private double value;

    public ArithmeticExprValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean addComponent(TextComponent component) {
        throw new UnsupportedOperationException("ArithmeticExpressionValue cannot contain subcomponents.");
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        throw new UnsupportedOperationException("ArithmeticExpressionValue cannot remove subcomponents.");
    }

    @Override
    public Iterator<TextComponent> iterator() {
        throw new UnsupportedOperationException("ArithmeticExpressionValue cannot iterate over not existing subcomponents.");
    }
}

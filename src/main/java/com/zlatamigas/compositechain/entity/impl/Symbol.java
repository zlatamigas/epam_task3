package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.Iterator;

public class Symbol implements TextComponent {

    private char symbol;

    public Symbol(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean addComponent(TextComponent component) {
        throw new UnsupportedOperationException("Symbol cannot contain subcomponents.");
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        throw new UnsupportedOperationException("Symbol cannot remove subcomponents.");
    }

    @Override
    public Iterator<TextComponent> iterator() {
        throw new UnsupportedOperationException("Symbol cannot iterate over not existing subcomponents.");
    }
}

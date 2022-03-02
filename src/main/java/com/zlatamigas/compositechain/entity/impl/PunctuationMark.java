package com.zlatamigas.compositechain.entity.impl;

import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.Iterator;

public class PunctuationMark implements TextComponent {

    private String punctuationMark;

    public PunctuationMark(String punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    public String getPunctuationMark() {
        return punctuationMark;
    }

    @Override
    public boolean addComponent(TextComponent component) {
        throw new UnsupportedOperationException("Punctuation mark cannot contain subcomponents.");
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        throw new UnsupportedOperationException("Punctuation mark cannot remove subcomponents.");
    }

    @Override
    public Iterator<TextComponent> iterator() {
        throw new UnsupportedOperationException("Punctuation mark cannot iterate over not existing subcomponents.");
    }
}

package com.zlatamigas.compositechain.entity;

public interface TextComponent extends Iterable<TextComponent> {
    int size();

    TextComponentType getType();

    boolean addComponent(TextComponent component);

    boolean removeComponent(TextComponent component);
}

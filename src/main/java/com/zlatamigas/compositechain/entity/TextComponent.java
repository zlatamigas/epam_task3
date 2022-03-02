package com.zlatamigas.compositechain.entity;

public interface TextComponent extends Iterable<TextComponent> {
    boolean addComponent(TextComponent component);
    boolean removeComponent(TextComponent component);
}

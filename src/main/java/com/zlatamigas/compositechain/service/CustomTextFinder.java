package com.zlatamigas.compositechain.service;

import com.zlatamigas.compositechain.entity.TextComponent;

import java.util.List;

public interface CustomTextFinder {
    List<TextComponent> find(TextComponent component);
}

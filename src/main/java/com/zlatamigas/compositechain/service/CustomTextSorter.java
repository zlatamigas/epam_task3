package com.zlatamigas.compositechain.service;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.exception.CustomTextException;

public interface CustomTextSorter {
    void sort(TextComponent component) throws CustomTextException;
}

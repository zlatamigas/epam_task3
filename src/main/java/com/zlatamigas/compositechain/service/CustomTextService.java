package com.zlatamigas.compositechain.service;

import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.exception.CustomTextException;

import java.util.List;
import java.util.Map;

public interface CustomTextService {
    void deleteSentencesWithWordCountLess(TextComponent textComponent, int wordCount) throws CustomTextException;

    Map<String, Integer> findWordFrequency(TextComponent textComponent) throws CustomTextException;

    List<TextComponent> findSentencesWithLongestWord(TextComponent component) throws CustomTextException;

    long countVowelInSentence(TextComponent sentenceComponent) throws CustomTextException;

    long countConsonantInSentence(TextComponent sentenceComponent) throws CustomTextException;
}

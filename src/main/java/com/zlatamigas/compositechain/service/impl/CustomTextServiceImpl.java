package com.zlatamigas.compositechain.service.impl;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.SimpleTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.service.CustomTextService;

import java.util.*;
import java.util.stream.Collectors;

public class CustomTextServiceImpl implements CustomTextService {

    public static final String VOWEL_REGEX = "[aAeEiIoOuU]";
    public static final String CONSONANT_REGEX = "[a-zA-Z&&[^aAeEiIoOuU]]";

    @Override
    public void deleteSentencesWithWordCountLess(TextComponent textComponent, int wordCount) throws CustomTextException {

        checkComplexTextComponent(textComponent, TextComponentType.TEXT);

        ((ComplexTextComponent) textComponent).getComponents()
                .forEach(paragraph -> {
                    List<TextComponent> sentencesToDelete = ((ComplexTextComponent) paragraph).getComponents().stream()
                            .filter(sentence -> ((ComplexTextComponent) sentence).getComponents().stream()
                                    .filter(word -> word.getType() == TextComponentType.WORD)
                                    .count() < wordCount).toList();
                    sentencesToDelete.forEach(paragraph::removeComponent);
                });
    }

    @Override
    public Map<String, Integer> findWordFrequency(TextComponent textComponent) throws CustomTextException {

        checkComplexTextComponent(textComponent, TextComponentType.TEXT);

        Map<String, Integer> wordWithFrequency = new HashMap<>();
        ((ComplexTextComponent) textComponent).getComponents().stream()
                .flatMap(paragraph -> ((ComplexTextComponent) paragraph).getComponents().stream())
                .flatMap(sentence -> ((ComplexTextComponent) sentence).getComponents().stream())
                .filter(sentencePart -> sentencePart.getType() == TextComponentType.WORD)
                .forEach(word -> {
                    String wordStr = ((ComplexTextComponent) word).getComponents().stream()
                            .map(letter -> ((SimpleTextComponent) letter).getSimpleTextComponent())
                            .collect(Collectors.joining()).toLowerCase(Locale.ROOT);

                    if (wordWithFrequency.containsKey(wordStr)) {
                        int wordFrequency = wordWithFrequency.get(wordStr) + 1;
                        wordWithFrequency.put(wordStr, wordFrequency);
                    } else {
                        wordWithFrequency.put(wordStr, 1);
                    }
                });

        return wordWithFrequency;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent textComponent) throws CustomTextException {

        checkComplexTextComponent(textComponent, TextComponentType.TEXT);

        ComplexTextComponent text = (ComplexTextComponent) textComponent;
        final int[] sizeOfLongestWord = {0};

        text.getComponents().stream()
                .flatMap(paragraph -> ((ComplexTextComponent) paragraph).getComponents().stream())
                .flatMap(sentence -> ((ComplexTextComponent) sentence).getComponents().stream())
                .filter(sentencePart -> sentencePart.getType() == TextComponentType.WORD)
                .forEach(word -> sizeOfLongestWord[0] = Math.max(word.size(), sizeOfLongestWord[0]));

        List<TextComponent> sentences = new ArrayList<>();

        text.getComponents().stream()
                .flatMap(paragraph -> ((ComplexTextComponent) paragraph).getComponents().stream())
                .forEach(sentence -> {

                    boolean hasLongest = false;
                    TextComponent sentencePart;

                    Iterator<TextComponent> sentencePartIterator = ((ComplexTextComponent) sentence).getComponents().iterator();
                    while (sentencePartIterator.hasNext() && !hasLongest) {
                        sentencePart = sentencePartIterator.next();
                        if (sentencePart.getType() == TextComponentType.WORD
                                && sentencePart.size() == sizeOfLongestWord[0]) {
                            hasLongest = true;
                            sentences.add(sentence);
                        }
                    }
                });

        return sentences;
    }

    @Override
    public long countVowelInSentence(TextComponent sentenceComponent) throws CustomTextException {

        return countLetterInSentenceByRegex(sentenceComponent, VOWEL_REGEX);
    }

    @Override
    public long countConsonantInSentence(TextComponent sentenceComponent) throws CustomTextException {

        return countLetterInSentenceByRegex(sentenceComponent, CONSONANT_REGEX);
    }

    private long countLetterInSentenceByRegex(TextComponent sentenceComponent, String regex) throws CustomTextException {

        checkComplexTextComponent(sentenceComponent, TextComponentType.SENTENCE);

        return ((ComplexTextComponent) sentenceComponent).getComponents().stream()
                .filter(sentenceComp -> sentenceComp.getType() == TextComponentType.WORD)
                .flatMap(word -> ((ComplexTextComponent) word).getComponents().stream())
                .filter(letter -> ((SimpleTextComponent) letter).getSimpleTextComponent().matches(regex))
                .count();
    }

    private void checkComplexTextComponent(TextComponent component, TextComponentType requiredType) throws CustomTextException {
        if (!(component instanceof ComplexTextComponent)) {
            throw new CustomTextException("Invalid TextComponent type: "
                    + component.getClass().getName() + ". Require ComplexTextComponent.");
        }
        if (component.getType() != requiredType) {
            throw new CustomTextException("Invalid ComplexTextComponent type: "
                    + component.getType() + ". Require " + requiredType.name() + " type.");
        }
    }
}

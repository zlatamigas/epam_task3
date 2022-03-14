package test.zlatamigas.compositechain.service.impl;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.parser.TextParser;
import com.zlatamigas.compositechain.reader.TextReader;
import com.zlatamigas.compositechain.reader.impl.TextReaderImpl;
import com.zlatamigas.compositechain.service.CustomTextService;
import com.zlatamigas.compositechain.service.impl.CustomTextServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CustomTextServiceImplTest {

    private static final String DEFAULT_TEXT_PATH = "testtexts/service_1.txt";
    private static final String EASY_TEXT_PATH = "testtexts/service_2.txt";

    private CustomTextService service;

    private ComplexTextComponent defaultText;
    private ComplexTextComponent easyText;

    @BeforeClass
    public void setUp() {
        TextParser parser = new TextParser();
        TextReader reader = new TextReaderImpl();
        service = new CustomTextServiceImpl();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(DEFAULT_TEXT_PATH);
            File file = new File(resource.toURI());
            String textStr = reader.readTextFromFile(file.getAbsolutePath());

            defaultText = new ComplexTextComponent(TextComponentType.TEXT);
            parser.handleParse(defaultText, textStr);

            resource = classLoader.getResource(EASY_TEXT_PATH);
            file = new File(resource.toURI());
            textStr = reader.readTextFromFile(file.getAbsolutePath());
            easyText = new ComplexTextComponent(TextComponentType.TEXT);
            parser.handleParse(easyText, textStr);

        } catch (URISyntaxException | CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testDeleteSentencesWithWordCountLess() {
        try {

            int expected = 5;

            service.deleteSentencesWithWordCountLess(defaultText, 10);
            int actual = defaultText.getComponents().stream()
                    .mapToInt(paragraph -> paragraph.size()).sum();

            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testFindWordFrequency() {

        Map<String, Integer> expected = Map.of(
                "it", 2,
                "is", 1,
                "a", 1,
                "established", 1,
                "at", 1,
                "its", 1,
                "layout", 1 ,
                "bye", 3
        );
        try {
            Map<String, Integer> actual = service.findWordFrequency(easyText);
            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(priority = 0)
    public void testFindSentencesWithLongestWord() {

        List<TextComponent> expected = new ArrayList<>();
        expected.add(((ComplexTextComponent) defaultText
                .getComponents().get(0))
                .getComponents().get(1));
        expected.add(((ComplexTextComponent) defaultText
                .getComponents().get(3))
                .getComponents().get(0));

        try {
            List<TextComponent> actual = service.findSentencesWithLongestWord(defaultText);
            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(priority = 0)
    public void testCountVowelInSentence() {

        TextComponent sentence = ((ComplexTextComponent) defaultText
                .getComponents().get(2))
                .getComponents().get(0);

        try {
            long expected = 28;
            long actual = service.countVowelInSentence(sentence);
            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(priority = 0)
    public void testCountConsonantInSentence() {

        TextComponent sentence = ((ComplexTextComponent) defaultText
                .getComponents().get(2))
                .getComponents().get(0);

        try {
            long expected = 38;
            long actual = service.countConsonantInSentence(sentence);
            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }
}
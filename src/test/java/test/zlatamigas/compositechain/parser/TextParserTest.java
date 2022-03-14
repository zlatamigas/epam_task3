package test.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.SimpleTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.parser.TextParser;
import com.zlatamigas.compositechain.reader.TextReader;
import com.zlatamigas.compositechain.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextParserTest {

    private static final String TEXT_PATH = "testtexts/parser_1.txt";

    private TextParser parser;
    private TextReader reader;

    private TextComponent expectedText;

    @BeforeClass
    public void setUp() {
        parser = new TextParser();
        reader = new TextReaderImpl();

        expectedText = new ComplexTextComponent(TextComponentType.TEXT);

        ComplexTextComponent paragraph;
        ComplexTextComponent sentence;
        ComplexTextComponent word;

        paragraph = new ComplexTextComponent(TextComponentType.PARAGRAPH);
        sentence = new ComplexTextComponent(TextComponentType.SENTENCE);
        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "I"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "t"));
        sentence.addComponent(word);

        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "i"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "s"));
        sentence.addComponent(word);


        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "a"));
        sentence.addComponent(word);

        SimpleTextComponent arithmeticExpressionValue =
                new SimpleTextComponent(TextComponentType.ARITHMETIC_EXCEPTION_VALUE, "-329.4166666666667");
        sentence.addComponent(arithmeticExpressionValue);
        sentence.addComponent(new SimpleTextComponent(TextComponentType.PUNCTUATION_MARK, "."));
        paragraph.addComponent(sentence);

        sentence = new ComplexTextComponent(TextComponentType.SENTENCE);
        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "E"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "s"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "t"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "a"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "b"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "l"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "i"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "s"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "h"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "e"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "d"));
        sentence.addComponent(word);
        sentence.addComponent(new SimpleTextComponent(TextComponentType.PUNCTUATION_MARK, ","));

        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "a"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "t"));
        sentence.addComponent(word);


        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "i"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "t"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "s"));
        sentence.addComponent(word);


        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "l"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "a"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "y"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "o"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "u"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "t"));
        sentence.addComponent(word);
        sentence.addComponent(new SimpleTextComponent(TextComponentType.PUNCTUATION_MARK, "."));
        paragraph.addComponent(sentence);
        expectedText.addComponent(paragraph);

        paragraph = new ComplexTextComponent(TextComponentType.PARAGRAPH);
        sentence = new ComplexTextComponent(TextComponentType.SENTENCE);
        word = new ComplexTextComponent(TextComponentType.WORD);
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "B"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "y"));
        word.addComponent(new SimpleTextComponent(TextComponentType.SYMBOL, "e"));
        sentence.addComponent(word);
        sentence.addComponent(new SimpleTextComponent(TextComponentType.PUNCTUATION_MARK, "."));
        paragraph.addComponent(sentence);
        expectedText.addComponent(paragraph);
    }

    @Test
    public void testHandleParse() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(TEXT_PATH);
            File file = new File(resource.toURI());
            String textStr = reader.readTextFromFile(file.getAbsolutePath());

            TextComponent actualText = new ComplexTextComponent(TextComponentType.TEXT);
            parser.handleParse(actualText, textStr);

            assertEquals(actualText, expectedText);
        } catch (URISyntaxException | CustomTextException e) {
            fail(e.getMessage());
        }
    }
}
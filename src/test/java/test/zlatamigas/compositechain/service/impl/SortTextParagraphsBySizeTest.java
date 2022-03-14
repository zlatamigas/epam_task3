package test.zlatamigas.compositechain.service.impl;

import com.zlatamigas.compositechain.entity.ComplexTextComponent;
import com.zlatamigas.compositechain.entity.TextComponent;
import com.zlatamigas.compositechain.entity.TextComponentType;
import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.parser.TextParser;
import com.zlatamigas.compositechain.reader.TextReader;
import com.zlatamigas.compositechain.reader.impl.TextReaderImpl;
import com.zlatamigas.compositechain.service.CustomTextSorter;
import com.zlatamigas.compositechain.service.impl.SortTextParagraphsBySize;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class SortTextParagraphsBySizeTest {

    private static final String TEXT_PATH = "testtexts/service_1.txt";
    private ComplexTextComponent defaultText;

    private CustomTextSorter sorter;

    @BeforeClass
    public void setUp() {

        sorter = new SortTextParagraphsBySize();

        TextParser parser = new TextParser();
        TextReader reader = new TextReaderImpl();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(TEXT_PATH);
            File file = new File(resource.toURI());
            String textStr = reader.readTextFromFile(file.getAbsolutePath());

            defaultText = new ComplexTextComponent(TextComponentType.TEXT);
            parser.handleParse(defaultText, textStr);
        } catch (URISyntaxException | CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSort() {
        List<TextComponent> expected = List.of(
                defaultText.getComponents().get(2),
                defaultText.getComponents().get(0),
                defaultText.getComponents().get(1),
                defaultText.getComponents().get(3));

        try {
            sorter.sort(defaultText);
            List<TextComponent> actual = defaultText.getComponents();

            assertEquals(actual, expected);
        } catch (CustomTextException e) {
            fail(e.getMessage());
        }
    }
}
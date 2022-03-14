package test.zlatamigas.compositechain.reader.impl;

import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.reader.TextReader;
import com.zlatamigas.compositechain.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.testng.Assert.*;

public class TextReaderImplTest {

    private static final String TEST_TEXTS_PATH = "testtexts/";

    private TextReader reader;

    @DataProvider(name = "textPr")
    public Object[][] createDataForAvg() {
        return new Object[][]{
                {"reader_1.txt", "\tLorem ipsum dolor sit amet, consectetur adipiscing elit.  Ut quis leo sit amet est fringilla vulputate at quis  mauris. Phasellus sed ipsum at ex tincidunt pharetra. Nulla facilisi. Nam efficitur magna  id lectus facilisis, a consequat erat rutrum. Proin faucibus risus at elit sollicitudin aliquam. Integer finibus ante ante, ac aliquet lorem venenatis et. Vivamus fermentum consequat dolor vel varius. Maecenas vehicula nisl vitae nunc cursus, vel facilisis mi rutrum. Sed semper augue mi, pulvinar laoreet turpis aliquet at. Nam elementum pretium velit eu condimentum. Vestibulum quis urna rhoncus, varius dolor vel, aliquet elit. Cras quis porttitor sapien. Morbi quis diam arcu. \tNullam quis dui eros. Integer viverra lacinia magna. Aliquam et scelerisque metus,  lobortis blandit metus. Aenean gravida elementum sem eu tristique. Pellentesque ornare, sem eu gravida dapibus, metus massa rhoncus lorem, eu mollis mi sapien at purus. Quisque rhoncus, nulla sed hendrerit ultricies, ipsum ex convallis nunc, pulvinar consequat justo ligula iaculis augue. Mauris nisl odio, rutrum eu pharetra et, ultricies ut mi. Fusce placerat bibendum ante, et volutpat nunc fermentum ac. Nulla nulla nisi, dapibus in felis a, pharetra dictum ante. Nam dapibus porttitor tellus eu tincidunt. Donec ornare elit urna, ac pretium tortor porta et. Morbi condimentum at risus a euismod. Nulla tempor, lorem sit amet feugiat tristique, eros risus accumsan justo, at iaculis felis orci eget nisl. \tMauris sagittis facilisis purus vitae laoreet. Nam lobortis metus id lectus  tristique auctor. Aenean tellus leo, fringilla ac aliquet eu, cursus a lorem. Mauris arcu neque, rhoncus et ex vitae, finibus venenatis enim. Vivamus quis ante nec enim vulputate consectetur. Nam pellentesque ante eget nibh sagittis, sed ullamcorper ante commodo. Donec convallis non dui at porttitor. Maecenas sed convallis mauris. Cras posuere consectetur ultricies. \tNunc ultricies, mauris nec imperdiet hendrerit,  lorem ante fringilla mauris, quis volutpat lacus ipsum maximus dui. Fusce tortor tortor, tristique nec sodales  a, ultricies et nisi. Curabitur blandit, lorem nec convallis porta, mauris enim molestie justo, feugiat  fermentum magna enim at diam. Pellentesque sit amet viverra massa. Proin consectetur lectus eu erat gravida,  ac eleifend orci efficitur. Maecenas leo nisi, porta vitae nisl at, sagittis ultricies ex. Pellentesque erat nulla, auctor eu euismod vitae, luctus condimentum risus. Etiam faucibus odio elementum lorem egestas iaculis. Mauris nec tempus est, aliquam sollicitudin arcu. Aenean nec mattis purus, auctor iaculis odio. Nunc maximus varius maximus. Mauris fermentum, sem id mollis venenatis, mi felis convallis tortor, eu sagittis sapien dolor sit amet lorem. In lobortis quis dui vitae porttitor. "},
                {"reader_2.txt", "\tHello, old friend. \t12+23*(12-13)/25-1. "},
                {"reader_3.txt", ""}
        };
    }

    @BeforeClass
    public void setUp() {
        reader = new TextReaderImpl();
    }

    @Test(dataProvider = "textPr")
    public void testReadTextFromFile(String textName, String expected) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(TEST_TEXTS_PATH + textName);
            File file = new File(resource.toURI());
            String actual = reader.readTextFromFile(file.getAbsolutePath());

            assertEquals(actual, expected);
        } catch (URISyntaxException | CustomTextException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = CustomTextException.class,
            expectedExceptionsMessageRegExp = "Error while reading file: " + TEST_TEXTS_PATH + "notexisting.txt")
    public void testReadTextFromNotExistingFile() throws CustomTextException {
        reader.readTextFromFile(TEST_TEXTS_PATH + "notexisting.txt");
    }
}
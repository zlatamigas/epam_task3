package com.zlatamigas.compositechain.reader.impl;

import com.zlatamigas.compositechain.exception.CustomTextException;
import com.zlatamigas.compositechain.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class TextReaderImpl implements TextReader {

    private static final Logger logger = LogManager.getLogger();

    private static final String TEXT_PARTS_DELIMITER = " ";

    @Override
    public String readTextFromFile(String filepath) throws CustomTextException {

        File file = new File(filepath);
        if (file.exists() && file.length() == 0) {
            return "";
        }

        logger.debug("Reader got access to file: " + filepath);

        String text;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            text = bufferedReader.lines().map(str -> str + TEXT_PARTS_DELIMITER).collect(Collectors.joining());

        } catch (IOException e) {
            throw new CustomTextException("Error while reading file: " + filepath, e);
        }

        logger.debug("Finished text reading");

        return text;
    }
}

package com.zlatamigas.compositechain.reader;

import com.zlatamigas.compositechain.exceptions.CustomTextException;

public interface TextReader {
    String readTextFromFile(String filepath) throws CustomTextException;
}

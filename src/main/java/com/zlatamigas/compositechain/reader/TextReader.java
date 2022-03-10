package com.zlatamigas.compositechain.reader;

import com.zlatamigas.compositechain.exception.CustomTextException;

public interface TextReader {
    String readTextFromFile(String filepath) throws CustomTextException;
}

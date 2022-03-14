package com.zlatamigas.compositechain.entity;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("\t", "\n"),
    SENTENCE,
    WORD(" "),
    SYMBOL,
    PUNCTUATION_MARK(" "),
    ARITHMETIC_EXCEPTION_VALUE(" ");

    private String prefix;
    private String postfix;

    TextComponentType() {
        prefix = "";
        postfix = "";
    }

    TextComponentType(String postfix) {
        this.prefix = "";
        this.postfix = postfix;
    }

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}

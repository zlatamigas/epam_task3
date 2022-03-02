package com.zlatamigas.compositechain.parser;

import com.zlatamigas.compositechain.entity.TextComponent;

public abstract class AbstractParserHandler {

    protected AbstractParserHandler successor;


    public AbstractParserHandler(AbstractParserHandler successor) {
        this.successor = successor;
    }

    public AbstractParserHandler() {
        successor = DefaultParserHandle.getHandleRequest();
    }

    public void setSuccessor(AbstractParserHandler successor) {
        this.successor = successor;
    }

    abstract public void handleParse(TextComponent component, String strToParse);


    private static class DefaultParserHandle extends AbstractParserHandler {

        private static DefaultParserHandle handler = new DefaultParserHandle();


        private DefaultParserHandle() {
        }


        public static DefaultParserHandle getHandleRequest() {
            return handler;
        }


        @Override
        public void handleParse(TextComponent component, String strToParse) {
        }

    }
}

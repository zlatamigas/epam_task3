package com.zlatamigas.compositechain.exceptions;

public class CustomTextException extends Exception{

    public CustomTextException(){
        super();
    }

    public CustomTextException(String message){
        super(message);
    }

    public CustomTextException(Throwable cause){
        super(cause);
    }

    public CustomTextException(String message, Throwable cause){
        super(message, cause);
    }
}

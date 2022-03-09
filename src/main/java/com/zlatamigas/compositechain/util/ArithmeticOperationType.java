package com.zlatamigas.compositechain.util;

public enum ArithmeticOperationType {
    ADD('+', 2),
    SUBTRACT('-', 2),
    MULTIPLY('*', 3),
    DIVIDE('/', 3),
    UNARY_MINUS('N', 4),
    BRACKET_OPEN('(', 1),
    BRACKET_CLOSE(')', -1);

    private final char operation;
    private final int priority;

    ArithmeticOperationType(char operation, int priority){
        this.operation = operation;
        this.priority = priority;
    }

    public char getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }

    public static boolean isArithmeticOperation(char arithmeticOperation){
        return ADD.operation == arithmeticOperation
                || SUBTRACT.operation == arithmeticOperation
                || MULTIPLY.operation == arithmeticOperation
                || DIVIDE.operation == arithmeticOperation;
    }

    public static ArithmeticOperationType getOperationType(char operation){
        for(ArithmeticOperationType operationType : values()){
            if (operationType.operation == operation){
                return  operationType;
            }
        }

        return null;
    }
}

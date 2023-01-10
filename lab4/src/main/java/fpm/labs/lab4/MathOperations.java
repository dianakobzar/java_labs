package fpm.labs.lab4;

public enum MathOperations {
    POW("^"),
    SIN("sin"),
    COS("cos"),
    TAN("tg"),
    COTAN("ctg");

    private final String operation;

    private MathOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}

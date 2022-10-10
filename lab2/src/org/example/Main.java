package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) throws RuntimeException {
        new Matrix(2, 2, List.of(1, 2, 3, 4)).print();
    }
}
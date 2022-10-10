package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableMatrix extends org.example.Matrix {

    public ImmutableMatrix(int row, int column, List<Integer> numbers) throws RuntimeException{
        if (row < 0 || column < 0 || (numbers.size() != row * column)) throw new RuntimeException();

        this.row = row;
        this.column = column;
        size = row * column;
        filled = true;

        List<List<Integer>> tempRow = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < row; i++) {
            List<Integer> tempColumn = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                tempColumn.add(numbers.get(counter));
                counter++;
            }
            tempRow.add(Collections.unmodifiableList(tempColumn));
        }
        values = Collections.unmodifiableList(tempRow);
    }

    public ImmutableMatrix(org.example.Matrix matrix) {
        this.row = matrix.row;
        this.column = matrix.column;
        size = row * column;
        filled = matrix.filled;

        List<List<Integer>> tempRow = new ArrayList<>();
        for (int i = 0; i < row; i++) tempRow.add(Collections.unmodifiableList(matrix.values.get(i)));
        values = Collections.unmodifiableList(tempRow);
    }
}
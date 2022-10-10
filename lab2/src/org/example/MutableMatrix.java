package org.example;

import java.util.List;

public class MutableMatrix extends org.example.Matrix {

    public MutableMatrix() {
        super();
    }

    public MutableMatrix(int row, int column) {
        super(row, column);
    }

    public MutableMatrix(int row, int column, List<Integer> numbers) throws RuntimeException {
        super(row, column, numbers);
    }

    public MutableMatrix(org.example.Matrix matrix) throws RuntimeException {
        super(matrix);
    }

    public void fillMatrix(List<Integer> numbers) throws RuntimeException {
        if (numbers.size() != row * column) throw new RuntimeException();
        filled = true;
        int counter = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                values.get(i).set(j, numbers.get(counter));
                counter++;
            }
    }

    public void toUpperTriangular() throws RuntimeException {
        if (row != column || !filled) throw new RuntimeException();
        for (int i = 0; i < row; i++) for (int j = 0; j < column; j++) if (i > j) values.get(i).set(j, 0);
    }

    public void toLowerTriangular() throws RuntimeException {
        if (row != column || !filled) throw new RuntimeException();
        for (int i = 0; i < row; i++) for (int j = 0; j < column; j++) if (i < j) values.get(i).set(j, 0);
    }

    public void setNumber(int row, int column, Integer number) throws RuntimeException {
        if (this.row < row || this.column < column || row < 0 || column < 0 || !filled) throw new RuntimeException();
        values.get(row).set(column, number);
    }

    public void setRow(int row, List<Integer> numbers) throws RuntimeException {
        if (this.row < row || row < 0 || !filled || column != numbers.size()) throw new RuntimeException();
        values.set(row, numbers);
    }

    public void setColumn(int column, List<Integer> numbers) throws RuntimeException {
        if (this.column < column || column < 0 || !filled || row != numbers.size()) throw new RuntimeException();
        int counter = 0;
        for (List<Integer> list : values) {
            list.set(column, numbers.get(counter));
            counter++;
        }
    }
}
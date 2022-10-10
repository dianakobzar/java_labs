package org.example;

import java.util.ArrayList;
import java.util.List;


public class Matrix{
    protected int row;
    protected int column;
    protected int size;

    protected List<List<Integer>> values;
    protected boolean filled;

    public Matrix() {

        row = 0;
        column = 0;
        size = 0;
        filled = false;

        values = new ArrayList<>();
    }

    public Matrix(int row, int column) {
        if (row < 0 || column < 0) throw new RuntimeException();

        this.row = row;
        this.column = column;
        size = row * column;
        filled = false;

        values = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) values.get(i).add(null);
        }
    }

    public Matrix(int row, int column, List<Integer> numbers) throws RuntimeException {
        if (row < 0 || column < 0 ||(numbers.size() != row * column)) throw new RuntimeException();
        this.row = row;
        this.column = column;
        size = row * column;
        filled = true;

        values = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) {
                values.get(i).add(numbers.get(counter));
                counter++;
            }
        }
    }

    public Matrix(Matrix matrix) {
        row = matrix.row;
        column = matrix.column;
        size = matrix.size;
        filled = matrix.filled;

        values = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) values.get(i).add(matrix.getNumber(i, j));
        }
    }

    public static Matrix diagonal(List<Integer> numbers) {
        if (numbers.size() <= 0) return new Matrix();

        ArrayList<Integer> values = new ArrayList<>();
        int size = numbers.size();
        int counter = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    values.add(numbers.get(counter));
                    counter++;
                } else values.add(0);
            }

        return new Matrix(size, size, values);
    }

    public Matrix upperTriangle() throws RuntimeException {
        if (row != column || !filled) throw new RuntimeException();
        ArrayList<Integer> newValues = new ArrayList<>();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                if (i > j) newValues.add(0);
                else newValues.add(values.get(i).get(j));
            }

        return new Matrix(row, column, newValues);
    }

    public Matrix lowerTriangle() throws RuntimeException {
        if (row != column || !filled) throw new RuntimeException();
        ArrayList<Integer> newValues = new ArrayList<>();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                if (i < j) newValues.add(0);
                else newValues.add(values.get(i).get(j));
            }

        return new Matrix(row, column, newValues);
    }

    public Integer getNumber(int row, int column) throws RuntimeException {
        if (this.row < row || this.column < column || row < 0 || column < 0 || !filled) throw new RuntimeException();
        return values.get(row).get(column);
    }

    public List<Integer> getRow(int row) throws RuntimeException {
        if (this.row < row || row < 0 || !filled) throw new RuntimeException();
        return values.get(row);
    }

    public List<Integer> getColumn(int column) throws RuntimeException {
        if (this.column < column || column < 0 || !filled) throw new RuntimeException();
        List<Integer> columnNumbers = new ArrayList<>();
        for (List<Integer> list : values) columnNumbers.add(list.get(column));
        return columnNumbers;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Matrix matrix)) return false;
        if (row != matrix.row || column != matrix.column || size != matrix.size || filled != matrix.filled)
            return false;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                if (!values.get(i).get(j).equals(matrix.getValues().get(i).get(j))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (row == 0 || column == 0 || size == 0 || !filled) return 0;

        int hash = 1;
        for (List<Integer> list : values) hash = 31 * hash + list.hashCode();
        return hash;
    }

    public void print() {
        for (List<Integer> list : values) System.out.println(list.toString());
        System.out.println();
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    public final int getSize() {
        return size;
    }

    public final String getSizeToString() {
        return row + " x " + column;
    }

    public final List<List<Integer>> getValues() {
        return values;
    }

    public final boolean isFilled() {
        return filled;
    }
}

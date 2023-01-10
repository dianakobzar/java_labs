package fpm.labs.lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @Test
    void setFuncPiecesShouldReturnFunctionHasProblemWithBrackets() {
        Function function = new Function("");
        assertEquals("Function wasn't written correctly. Must be a problem with brackets.",
                function.setFuncPieces("(six(x)) *     2))"));
    }

    @Test
    void setFuncPiecesShouldReturnFinishedFine() {
        Function function = new Function("");
        assertEquals("Finished fine.",
                function.setFuncPieces("cos(x)*2/3*sin(x)"));
    }

    @Test
    void baseOperationsShouldThrowAnException() {
        Function function = new Function("");
        Exception exception = assertThrows(Exception.class, () -> function.baseOperations("&", 1d, 1d));
        String expectedMessage = "Incorrect operation.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void baseOperationsShouldReturnExpectedValueWithPlusOperation() throws Exception {
        Function function = new Function("");
        assertEquals(7, function.baseOperations("+", 3d, 4d));
        assertEquals(7, function.baseOperations("+", 4d, 3d));
    }

    @Test
    void baseOperationsShouldReturnExpectedValueWithMinusOperation() throws Exception {
        Function function = new Function("");
        assertEquals(1, function.baseOperations("-", 3d, 4d));
        assertEquals(-1, function.baseOperations("-", 4d, 3d));
    }

    @Test
    void baseOperationsShouldReturnExpectedValueWithMultiplyOperation() throws Exception {
        Function function = new Function("");
        assertEquals(12, function.baseOperations("*", 3d, 4d));
        assertEquals(12, function.baseOperations("*", 4d, 3d));
    }

    @Test
    void baseOperationsShouldReturnExpectedValueWithDivideOperation() throws Exception {
        Function function = new Function("");
        assertEquals(4d/3d, function.baseOperations("/", 3d, 4d));
        assertEquals(3d/4d, function.baseOperations("/", 4d, 3d));
    }

    @Test
    void replaceAllButNotNumbersOrWithXShouldThrowAnException() {
        Function function = new Function("");
        Exception exception = assertThrows(Exception.class, () -> function.replaceAllButNotNumbersOrWithX("", 1.0));
        String expectedMessage = "Operand is empty or null value!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
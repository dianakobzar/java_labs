package fpm.labs.lab4;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Function {

    private List<String> funcPieces = new ArrayList<>();

    public Function(String function) {
        if (function.isEmpty()) {
            String defaultFunction = "x^2*sin(x)/2*tg(x)";
            setFuncPieces(defaultFunction);
            return;
        }
        setFuncPieces(function);
    }

    public List<String> getFuncPieces() {
        return funcPieces;
    }

    public String setFuncPieces(String function) {
        if (StringUtils.countMatches(function, '(') != StringUtils.countMatches(function, ')')) {
            return "Function wasn't written correctly. Must be a problem with brackets.";
        }
        function = function.replaceAll("\\s+", "");
        int counterLeftBracket = 0;
        int counterRightBracket = 0;
        int start = 0;
        for (int end = start; end < function.length(); end++) {
            if (function.charAt(end) == '(') {
                counterLeftBracket++;
            } else if (function.charAt(end) == ')') {
                counterRightBracket++;
            }
            if (counterRightBracket > counterLeftBracket) {
                return "Function wasn't written correctly. Must be a problem with brackets.";
            }
            if (counterLeftBracket != 0 && counterLeftBracket == counterRightBracket) {
                funcPieces.add(function.substring(start, end + 1));
                start = end + 1;
                counterLeftBracket = 0;
                counterRightBracket = 0;
            }
        }
        if (function.charAt(0) == '(')
            funcPieces.set(0, funcPieces.get(0).substring(0, funcPieces.get(0).length() - 1));
        List<List<String>> listOfLists = funcPieces.stream()
                .map(s -> Arrays.stream(s.split("(?=[+\\-*/^])")).toList()).toList();
        funcPieces = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        return "Finished fine.";
    }

    public Double calculateFunction(Double x) throws Exception {
        Double result = 0d;
        if (funcPieces.size() == 1) {
            return baseOperations("+",
                    calculateFunctionPart(funcPieces.get(0), x),
                    result);
        }
        for (int i = 1; i < funcPieces.size(); i++) {
            String operation = String.valueOf(funcPieces.get(i).charAt(0));
            if (operation.equals(MathOperations.POW.getOperation())) {
                double doubleA = replaceAllButNotNumbersOrWithX(funcPieces.get(i - 1), x);
                double doubleB = replaceAllButNotNumbersOrWithX(funcPieces.get(i), x);
                result += Math.pow(doubleA, doubleB);
                continue;
            }
            result = baseOperations(
                    operation,
                    calculateFunctionPart(funcPieces.get(i), x),
                    result);
        }
        return result;
    }

    public Double baseOperations(String operation, Double operand, Double result) throws Exception {
        if (operation.equals("+")) {
            return operand + result;
        }
        if (operation.equals("-")) {
            return result - operand;
        }
        if (operation.equals("*")) {
            return result * operand;
        }
        if (operation.equals("/")) {
            return result / operand;
        }
        throw new Exception("Incorrect operation.");

    }

    public Double calculateFunctionPart(String operand,
                                        Double x) throws Exception {
        if (operand.contains(MathOperations.SIN.getOperation())) {
            return Math.sin(
                    replaceAllButNotNumbersOrWithX(operand, x)
            );
        }
        if (operand.contains(MathOperations.COS.getOperation())) {
            return Math.cos(
                    replaceAllButNotNumbersOrWithX(operand, x)
            );
        }
        if (operand.contains(MathOperations.TAN.getOperation())) {
            return Math.tan(
                    replaceAllButNotNumbersOrWithX(operand, x)
            );
        }
        if (operand.contains(MathOperations.COTAN.getOperation())) {
            return 1d / Math.tan(
                    replaceAllButNotNumbersOrWithX(operand, x)
            );
        }
        return replaceAllButNotNumbersOrWithX(operand, x);
    }

    /**
     * Returns a double value of any String or throws the Exception
     */
    public Double replaceAllButNotNumbersOrWithX(String operand, Double x) throws Exception {
        if (operand == null || operand.equals("")) {
            throw new Exception("Operand is empty or null value!");
        }
        operand = operand.contains("(") ? operand.substring(operand.indexOf("(") + 1, operand.indexOf(")")) : operand;
        return operand.equals("x") ? x : Double.parseDouble(operand.replaceAll("\\D+", ""));
    }

    @Override
    public String toString() {
        return "Function{" +
                "funcPieces=" + funcPieces +
                '}';
    }
}

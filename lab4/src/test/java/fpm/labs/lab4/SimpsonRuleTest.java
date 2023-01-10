package fpm.labs.lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpsonRuleTest {

    public static double f1(double x) {
        return Math.pow(x, 2) * Math.sin(x) / 2 * Math.tan(x);
    }

    public static double fSin(double x) {
        return Math.sin(x);
    }

    public static double fCos(double x) {
        return Math.cos(x);
    }

    public static double fTan(double x) {
        return Math.tan(x);
    }

    @FunctionalInterface
    public interface MyInterface {
        double f(double x);
    }

    public double integrate(double a, double b, int N, MyInterface functionalInterfaceMethod) {
        double h = (b - a) / N ;     // step size

        double sumDefault = 1.0 / 3.0 * (functionalInterfaceMethod.f(a) + functionalInterfaceMethod.f(b));

        for (int i = 1; i < N; i += 2) {
            double x = a + h * i;
            sumDefault += 4.0 / 3.0 * functionalInterfaceMethod.f(x);
        }

        for (int i = 2; i < N; i += 2) {
            double x = a + h * i;
            sumDefault += 2.0 / 3.0 * functionalInterfaceMethod.f(x);
        }
        return sumDefault * h;
    }

    @Test
    void integrateDefaultFunctionShouldEqualIntegrateFunctionF1() throws Exception {
        Function function = new Function("");
        SimpsonRule simpsonRule = new SimpsonRule(function);
        double a = -3;
        double b = 3;
        int N = 10000;
        assertEquals(simpsonRule.integrate(a, b, N),
                integrate(a, b, N, SimpsonRuleTest::f1));
    }

    @Test
    void integrateSomeFunctionShouldEqualIntegrateFunctionSin() throws Exception {
        Function function = new Function("sin(x)");
        SimpsonRule simpsonRule = new SimpsonRule(function);
        double a = -3;
        double b = 3;
        int N = 10000;
        assertEquals(simpsonRule.integrate(a, b, N),
                integrate(a, b, N, SimpsonRuleTest::fSin));
    }

    @Test
    void integrateSomeFunctionShouldEqualIntegrateFunctionCos() throws Exception {
        Function function = new Function("cos(x)");
        SimpsonRule simpsonRule = new SimpsonRule(function);
        double a = -3;
        double b = 3;
        int N = 10000;
        assertEquals(simpsonRule.integrate(a, b, N),
                integrate(a, b, N, SimpsonRuleTest::fCos));
    }

    @Test
    void integrateSomeFunctionShouldEqualIntegrateFunctionTan() throws Exception {
        Function function = new Function("tg(x)");
        SimpsonRule simpsonRule = new SimpsonRule(function);
        double a = -3;
        double b = 3;
        int N = 10000;
        assertEquals(simpsonRule.integrate(a, b, N),
                integrate(a, b, N, SimpsonRuleTest::fTan));
    }

}
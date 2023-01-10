package fpm.labs.lab4;

import java.util.Scanner;

public class Lab4Application {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        double a, b;
        int N;
        System.out.print("Enter a: ");
        a = in.nextDouble();
        System.out.print("Enter b: ");
        b = in.nextDouble();
        System.out.print("Enter N: ");
        N = in.nextInt();

        String myFunction;
        System.out.print("Enter your function or use default(type d): ");
        in.nextLine();
        myFunction = in.nextLine();

        Function function = new Function(myFunction.equals("d") ? "":myFunction);
        System.out.println(function.getFuncPieces());

        SimpsonRule simpsonRule = new SimpsonRule(function);

        boolean lang;
        System.out.print("Choose result language(u for ukrainian, e for english): ");
        lang = in.nextLine().charAt(0) == 'u';
        System.out.println(lang ? "---РЕЗУЛЬТАТ---":"---RESULT---");
        System.out.print(lang ? "Функція: ":"Function " + simpsonRule.getF());
        System.out.println(lang ? "Алгоритм інтегрування: метод Сімпсона":"Integration algorithm: Simpsons rule");
        System.out.println(lang ? "Інтервал, крок: ":"Interval, step: " + "[" + a + ", " + b + "], " + (b - a) / N);
        System.out.println(lang ? "Результат інтегрування: ":"Integration result: " + simpsonRule.integrate(a, b, N));
    }

}

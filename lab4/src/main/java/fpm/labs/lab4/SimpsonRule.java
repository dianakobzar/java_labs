package fpm.labs.lab4;

public class SimpsonRule {

    private Function f;

    public SimpsonRule(Function f) {
        this.f = f;
    }

    public Function getF() {
        return f;
    }

    public double integrate(double a, double b, int N) throws Exception {
        if (f == null) {
            f = new Function("");
        }

        double h = (b - a) / (N - 1);     // step size

        double sum = 1.0 / 3.0 * (f.calculateFunction(a) + f.calculateFunction(b));

        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * f.calculateFunction(x);
        }

        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * f.calculateFunction(x);
        }
        return sum * h;
    }
}

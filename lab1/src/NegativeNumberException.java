import java.util.ArrayList;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(ArrayList<Integer> numbers)
    {
        super("Negatives not allowed: " + numbers.toString());
    }
}

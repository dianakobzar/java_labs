 import java.util.*;

    public class Main {

        public static void main(String[] args) {

            StringCalculator stringCalculator = new StringCalculator();

            Scanner scanner = new Scanner(System.in);

            String input;

            while(!Objects.equals(input = scanner.nextLine(), "stop")) {
                try {
                    System.out.println('"' + input + "\" == " + stringCalculator.add(input));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


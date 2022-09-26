import java.util.ArrayList;
//import java.util.Collections;
import java.util.Comparator;

public class StringCalculator {

    public int add(String numbers) throws NegativeNumberException {
        if(numbers.equals("")) return 0; //перевірка на пустий рядок

        ArrayList<String> delimiters = new ArrayList<>();
        if(numbers.length() > 2 && numbers.charAt(0) == '/' && numbers.charAt(1) == '/') { //перевірка на нові деліметри
            if(numbers.charAt(2) == '[') { // якщо задаємо нові деліметри
                int countedChars = 0;
                do {
                    countedChars++;
                    StringBuilder stringBuilder = new StringBuilder();
                    do {
                        stringBuilder.append(numbers.charAt(2 + countedChars));
                        countedChars++;
                    } while (numbers.charAt(2 + countedChars) != ']');
                    delimiters.add(stringBuilder.toString());
                    countedChars++;
                } while (numbers.charAt(2 + countedChars) == '[');
                numbers = numbers.substring(3 + countedChars);
            } else {
                delimiters.add("" + numbers.charAt(2));
                numbers = numbers.substring(4);
            }
        }
        delimiters.add(","); // щоб "," була деліметром завжди
        if(numbers.equals("")) return 0;
        delimiters.sort(Comparator.reverseOrder());//сортування деліметрів по спаданню

        int sum = 0;

        int firstI = 0, secondI = 0;

        boolean negativesFlag = false;
        ArrayList<Integer> negatives = new ArrayList<>();

        while(secondI <= numbers.length()) {
            boolean isDelimiter = false;
            int delimiterLength = 0;

            if (secondI != numbers.length()) {
                for(String delimiter : delimiters) if(numbers.charAt(secondI) == delimiter.charAt(0)) {

                    if (numbers.startsWith(delimiter, secondI)) {

                        isDelimiter = true;
                        delimiterLength = delimiter.length();
                        break;
                    }
                }
            }

            if((secondI == numbers.length() || isDelimiter || numbers.charAt(secondI) == '\n') && firstI != secondI) { //знаходимо коли наступний символ деліметр
                int number = Integer.parseInt(numbers.substring(firstI, secondI));
                if (number < 0) {
                    negativesFlag = true;
                    negatives.add(number); //перевірка на негативні і додаємо у список негативні числа
                } else if (number > 0 && number < 1001) sum += number;//ігнорування чисел більших за 1000
                secondI += (secondI != numbers.length() && numbers.charAt(secondI) == '\n') ? 1 : delimiterLength;
                firstI = secondI;
            }
            else secondI++;
        }

        if(negativesFlag) throw new NegativeNumberException(negatives); //якщо є негативні числа

        return sum;
    }
}
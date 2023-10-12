import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiterRegex = ",|\\\\n"; 

        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.+\\]+)\\\\n(.+)").matcher(numbers);
            if (matcher.matches()) {
                String customDelimiters = matcher.group(1);
                customDelimiters = customDelimiters.substring(1, customDelimiters.length() - 1); 
                String[] customDelimiterArray = customDelimiters.split("\\]\\[");
                for (String customDelimiter : customDelimiterArray) {
                    delimiterRegex += "|" + Pattern.quote(customDelimiter); 
                }
                delimiterRegex += "|,|\\\\n"; 
                numbers = matcher.group(2);
            }
        }

        String[] numberArray = numbers.split(delimiterRegex);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String number : numberArray) {
            try {
                int parsedNumber = Integer.parseInt(number);
                if (parsedNumber < 0) {
                    negatives.add(parsedNumber);
                } else if (parsedNumber <= 1000) { 
                    sum += parsedNumber;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format");
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter numbers separated by custom delimiters and/or '\\n' (e.g., //[*][%]\\n1*2%3): ");
        String input = scanner.nextLine();

        scanner.close();

        try {
            int result = Add(input);
            System.out.println("Sum of numbers: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

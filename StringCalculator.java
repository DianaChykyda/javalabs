import java.util.Scanner;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String[] numberArray = numbers.split(",");
        int sum = 0;

        for (String number : numberArray) {
            String[] subNumbers = number.split("\\\\n"); 
            for (String subNumber : subNumbers) {
                try {
                    int parsedNumber = Integer.parseInt(subNumber);
                    sum += parsedNumber;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid input format");
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter numbers separated by commas and/or '\\n' (e.g., 1,2\\n4): ");
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

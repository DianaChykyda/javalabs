import java.util.Scanner;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String[] numberArray = numbers.split(",");
        if (numberArray.length > 2) {
            throw new IllegalArgumentException("Too many numbers");
        }
        int sum = 0;

        for (String number : numberArray) {
            try {
                int parsedNumber = Integer.parseInt(number);
                sum += parsedNumber;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format");
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        scanner.close();

        try {
            int result = Add(input);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


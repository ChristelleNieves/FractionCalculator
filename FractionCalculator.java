// All code below was written by Christelle Nieves

import java.util.Scanner;

public class FractionCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String op = "0";

        // Print the welcome message
        welcomeMessage();

        // Main loop. This is where all the work will be done.
        while (!op.equals("q") && !op.equals("Q")) {
            System.out.println("-------------------------------------------------------------");

            // Get the operation from the user (+,-,*,/,=,Q)
            op = getOperation(input);

            // If the user didn't choose to quit, ask them for the fractions
            if (!op.equals("q") && !op.equals("Q")) {
                Fraction f1 = getFraction(input);
                Fraction f2 = getFraction(input);

                // Perform the specified operation on the given fractions
                // If the operation is "=" we will just print it here without calling any other methods
                if (op.equals("=")) {
                    boolean equal = f1.equals(f2);
                    System.out.println(f1.toString() + " " + op + " " + f2.toString() + " is " + equal);
                } else {
                    // Call performOperation() to calculate the resulting fraction
                    Fraction result = performOperation(op, f1, f2);

                    // Reduce the resulting fraction to lowest terms and print the result
                    result.toLowestTerms();
                    System.out.println(f1.toString() + " " + op + " " + f2.toString() + " = " + result.toString());
                }
            }
        }
    }

    // Performs a specified operation on two fractions and returns the resulting fraction
    public static Fraction performOperation(String op, Fraction f1, Fraction f2) {
        Fraction result = new Fraction();
        switch (op) {
            case "+": {
                result = f1.add(f2);
                break;
            }
            case "-": {
                result = f1.subtract(f2);
                break;
            }
            case "*": {
                result = f1.multiply(f2);
                break;
            }
            case "/": {
                result = f1.divide(f2);
                break;
            }
            default:
                break;
        }
        return result;
    }

    // Display a welcome message to the screen with the rules of the calculator
    public static void welcomeMessage() {
        System.out.println("This program is a fraction calculator\n" +
                "It will add, subtract, multiply and divide fractions until you type Q to quit.\n" +
                "Please enter your fractions in the form a/b, where a and b are integers.");
    }

    // Get a string from the user representing a mathematical operation
    public static String getOperation(Scanner input) {
        String op = "";

        // Keep asking for an operation until the user enters a valid one
        while (!validOperation(op)) {
            System.out.print("Please enter an operation (+, -, /, *, =, or Q to quit):");
            op = input.next();
        }

        return op;
    }

    // Return true if the string represents a valid operation, false otherwise.
    public static boolean validOperation(String c) {
        return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") ||
                c.equals("=") || c.equals("q") || c.equals("Q"));
    }

    // Return true if the given string represents a valid fraction, false otherwise.
    public static boolean validFraction(String s) {
        boolean negative = false;

        // The numerator may be negative. In this case, remove the negative sign.
        if (s.startsWith("-")) {
            negative = true;
            s = s.substring(1); // Remove the "-" character
        }

        // If the fraction is not a whole number, split the string into numerator/denominator substrings.
        if (s.contains("/")) {
            String[] split = s.split("/");

            // Check if the string represents a valid integer
            try {
                int n = Integer.parseInt(split[0]);

                // Add the negative sign onto the integer now if applicable
                if (negative) {
                    n = -n;
                }

                int d = Integer.parseInt(split[1]);

                // If the denominator is zero, return false (no division by zero)
                if (d == 0) {
                    return false;
                }

            } catch (NumberFormatException nfe) {
                return false;
            }
        } else {
            // Make sure the integer is valid
            try {
                int n = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    }

    // Get a string from the user representing a fraction in the form a/b or a
    public static Fraction getFraction(Scanner input) {
        System.out.print("Please enter a fraction (a/b) or integer (a):");
        String fraction = input.next();

        // Keep asking for a fraction until the user enters a valid one
        while (!validFraction(fraction)) {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            fraction = input.next();
        }

        // Split the string into numerator/denominator substrings
        String[] split = fraction.split("/");

        // If the fraction is not a whole number(has a denominator), create a new fraction with both parts
        if (split.length > 1) {
            return new Fraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        // If the fraction is a whole number, create a fraction with just the numerator
        return new Fraction(Integer.parseInt(split[0]));
    }
}
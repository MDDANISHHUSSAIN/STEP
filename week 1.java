// 1. Rock-Paper-Scissors Game

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    // Method to determine the winner
    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int wins = 0;
        int losses = 0;
        int draws = 0;

        String[] playerMoves = new String[5];
        String[] computerMoves = new String[5];
        String[] results = new String[5];

        // 5 rounds
        for (int i = 0; i < 5; i++) {

            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = sc.nextLine();

            // Computer randomly selects a move
            String computerMove = moves[random.nextInt(3)];

            // Determine result
            String result = playRound(playerMove, computerMove);

            // Store round details
            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            System.out.println("Computer Move: " + computerMove);
            System.out.println("Result: " + result);
            System.out.println();

            // Count results
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        // Summary table
        System.out.println("\n----- FINAL SUMMARY -----");
        System.out.println("Round | Player Move | Computer Move | Result");

        for (int i = 0; i < 5; i++) {
            System.out.println(
                (i + 1) + "     | " +
                playerMoves[i] + "       | " +
                computerMoves[i] + "       | " +
                results[i]
            );
        }

        // Calculate win percentage
        double winPercentage = (wins / 5.0) * 100;

        System.out.println("\nWins   : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("Draws  : " + draws);
        System.out.println("Win %  : " + winPercentage + "%");

        sc.close();
    }
}


// 2. Palindrome Checker
import java.util.Scanner;

public class PalindromeChecker {

    // 1. Iterative approach
    static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // 2. Recursive approach
    static boolean isPalindromeRecursive(String text) {

        return recursiveCheck(text, 0, text.length() - 1);
    }

    static boolean recursiveCheck(String text, int left, int right) {

        // Base condition
        if (left >= right) {
            return true;
        }

        // Characters don't match
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        // Check remaining substring
        return recursiveCheck(text, left + 1, right - 1);
    }

    // 3. Array reversal approach
    static boolean isPalindromeArrayReversal(String text) {

        char[] arr = text.toCharArray();

        // Reverse the array
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        String reversed = new String(arr);

        return text.equals(reversed);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = sc.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean array = isPalindromeArrayReversal(text);

        System.out.println();

        System.out.println("Iterative: " +
                (iterative ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: " +
                (recursive ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (array ? "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}


// 3. BMI Calculator for a Team
import java.util.Scanner;

public class BMICalculator {

    // Method to classify BMI
    static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } 
        else if (bmi < 25) {
            return "Normal";
        } 
        else if (bmi < 30) {
            return "Overweight";
        } 
        else {
            return "Obese";
        }
    }

    // Method to print the report
    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("\n-------------------------------------------------------------");
        System.out.println("Person\tHeight(m)\tWeight(kg)\tBMI\tStatus");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            String status = getBmiStatus(bmi);

            System.out.printf("%d\t%.2f\t\t%.2f\t\t%.2f\t%s%n",
                    i + 1, heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 10;

        double[] heights = new double[n];
        double[] weights = new double[n];

        // Input
        for (int i = 0; i < n; i++) {

            System.out.print("Enter height for Person " + (i + 1) + " (m): ");
            heights[i] = sc.nextDouble();

            System.out.print("Enter weight for Person " + (i + 1) + " (kg): ");
            weights[i] = sc.nextDouble();
        }

        // Print report
        printWellnessReport(heights, weights);

        sc.close();
    }
}

// 4. First Non-Repeating Character
import java.util.Scanner;

public class FirstNonRepeating {

    static char findFirstNonRepeatingChar(String text) {

        // Count frequency of each character
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        // Find first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0';   // No non-repeating character
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: " + result);
        }

        sc.close();
    }
}

// 5. Reverse Customer Name

import java.util.Scanner;

public class ReverseCustomerName {

    // Method to reverse the name
    static String reverseCustomerName(String customerName) {

        String reversed = "";

        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed = reversed + customerName.charAt(i);
        }

        return reversed;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        String reversedName = reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);

        sc.close();
    }
}

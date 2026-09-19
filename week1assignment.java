// 1. The Exam Hall Seat Duplication Checker
import java.util.Scanner;

public class SeatChecker {

    static void checkDuplicateSeats(int[] seatNumbers) {

        boolean found = false;

        // Compare every seat with every other seat
        for (int i = 0; i < seatNumbers.length; i++) {

            for (int j = i + 1; j < seatNumbers.length; j++) {

                if (seatNumbers[i] == seatNumbers[j]) {

                    System.out.println("Duplicate Seat Number Found: "
                                       + seatNumbers[i]);

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] seatNumbers = new int[n];

        System.out.println("Enter seat numbers:");

        for (int i = 0; i < n; i++) {
            seatNumbers[i] = sc.nextInt();
        }

        checkDuplicateSeats(seatNumbers);

        sc.close();
    }
}


// 2. The Typing Speed Test Accuracy Checker
import java.util.Scanner;

public class TypingAccuracy {

    static void checkTypingAccuracy(String original, String typed) {

        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {

            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } 
            else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;

        System.out.println("Matched: " + matched + "/" + original.length());
        System.out.printf("Accuracy: %.2f%%%n", accuracy);

        if (firstMismatch == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position "
                    + (firstMismatch + 1)
                    + " ('"
                    + original.charAt(firstMismatch)
                    + "' vs '"
                    + typed.charAt(firstMismatch)
                    + "')");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original text: ");
        String original = sc.nextLine();

        System.out.print("Enter typed text: ");
        String typed = sc.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Error: Strings must have equal length.");
        } else {
            checkTypingAccuracy(original, typed);
        }

        sc.close();
    }
}

// 3. The Traffic Signal Streak Analyzer
import java.util.Scanner;

public class TrafficStreak {

    static void findLongestStreak(String signalLog) {

        int currentCount = 1;
        int longestCount = 1;

        char currentColor = signalLog.charAt(0);
        char longestColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (currentCount > longestCount) {
                longestCount = currentCount;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + longestColor
                + "' repeated " + longestCount + " times");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter signal log: ");
        String signalLog = sc.nextLine();

        findLongestStreak(signalLog);

        sc.close();
    }
}

// 4. The Warehouse Inventory Balancer
import java.util.Scanner;

public class InventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        // Calculate totals
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];
        }

        // Check balance
        String status;

        if (totalA == totalB) {
            status = "Balanced";
        } else {
            status = "Not Balanced";
        }

        // Find highest quantity
        int highest = sectionA[0];
        String section = "Section A";
        int index = 0;

        // Check Section A
        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                index = i;
            }
        }

        // Check Section B
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);
        System.out.println("Status: " + status);
        System.out.println("Highest Quantity: " + highest
                + " (" + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter Section A quantities:");
        for (int i = 0; i < n; i++) {
            sectionA[i] = sc.nextInt();
        }

        System.out.println("Enter Section B quantities:");
        for (int i = 0; i < n; i++) {
            sectionB[i] = sc.nextInt();
        }

        analyzeInventory(sectionA, sectionB);

        sc.close();
    }
}

// 5. The Movie Review Word Length Profiler
import java.util.Scanner;

public class WordLengthProfiler {

    static void classifyWordLengths(String review) {

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        // Split sentence into words
        String[] words = review.split(" ");

        // Check each word
        for (int i = 0; i < words.length; i++) {

            int length = words[i].length();

            if (length >= 1 && length <= 4) {
                shortWords++;
            }
            else if (length >= 5 && length <= 8) {
                mediumWords++;
            }
            else {
                longWords++;
            }
        }

        System.out.println("Short: " + shortWords);
        System.out.println("Medium: " + mediumWords);
        System.out.println("Long: " + longWords);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = sc.nextLine();

        classifyWordLengths(review);

        sc.close();
    }
}
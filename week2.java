// 1. Vowel & Consonant Counter
public class VowelConsonantCounter {

    static void countVowelsAndConsonants(String text) {

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {

            char ch = Character.toLowerCase(text.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u') {

                vowels++;

            } else if (ch >= 'a' && ch <= 'z') {

                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels +
                           " | Consonants: " + consonants);
    }

    public static void main(String[] args) {

        String text = "Java Programming";

        countVowelsAndConsonants(text);
    }
}


// 2. CSV Student Record Parser
public class CSVStudentRecordParser {

    static void parseStudentRecord(String csvLine) {

        String[] fields = csvLine.split(",");

        // Check whether exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0];
        String rollNumber = fields[1];
        String department = fields[2];

        System.out.println(
            "Name: " + name +
            " | Roll No: " + rollNumber +
            " | Dept: " + department
        );
    }

    public static void main(String[] args) {

        String csvLine = "Ananya Verma,RA2211003010123,CSE";

        parseStudentRecord(csvLine);
    }
}

// 3. File Extension Validator
import java.util.Scanner;

public class FileExtensionValidator {

    static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String filename = sc.nextLine();

        System.out.println(validateFileExtension(filename));

        sc.close();
    }
}

// 4. Masked Phone Number Formatter
import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    static String formatPhoneNumber(String phone) {

        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder result = new StringBuilder();

        result.append("XXXXXX");
        result.append("-");
        result.append(phone.substring(6));

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String phone = sc.nextLine();

        System.out.println(formatPhoneNumber(phone));

        sc.close();
    }
}

//Bank transaction reference generator and validator
import java.util.Scanner;

public class BankTransactionReference {

    static String normalizeReference(String raw) {
        raw = raw.trim();

        String bankCode = raw.substring(0, 3).toUpperCase();
        String rest = raw.substring(3);

        return bankCode + rest;
    }

    static String validateAndFormat(String reference) {

        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(bankCode);
        result.append("] DATE: ");
        result.append(date.substring(0, 2));
        result.append("/");
        result.append(date.substring(2, 4));
        result.append("/");
        result.append(date.substring(4, 6));
        result.append(" | SEQ: ");
        result.append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String raw = sc.nextLine();

        String normalized = normalizeReference(raw);

        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}
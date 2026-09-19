// M1. from Parallel arrays to  a class
class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    // Constructor
    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    // Instance method
    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }
}

public class Main {
    public static void main(String[] args) {

        // Array of PlacementRecord objects
        PlacementRecord[] records = {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };

        // Print each record
        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}

// M2.Encapsulated mess-card wallet
class MessWallet {

    private double balance;

    // Constructor
    public MessWallet(double openingBalance) {

        if (openingBalance < 0) {
            System.out.println("Warning: Negative balance not allowed. Starting with 0.");
            balance = 0;
        } else {
            balance = openingBalance;
        }
    }

    // Add money
    public void topUp(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid top-up amount.");
        } else {
            balance = balance + amount;
            System.out.println("Top-up successful.");
        }
    }

    // Deduct money
    public void deduct(double amount) {

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Invalid deduction amount.");
        } else {
            balance = balance - amount;
            System.out.println("Amount deducted successfully.");
        }
    }

    // Read-only access
    public double getBalance() {
        return balance;
    }
}

public class Main {

    public static void main(String[] args) {

        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        wallet.deduct(100);

        System.out.println("Current Balance: " + wallet.getBalance());

        // Not allowed:
        // wallet.balance = 10000;
    }
}

// M3. overloaded constructor for a course 
class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    // 4-argument constructor
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // 3-argument constructor
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    // Calculate total credits
    public int totalCredits() {
        return credits + labCredits;
    }
}

public class Main {
    public static void main(String[] args) {

        // Theory-only course
        Course c1 = new Course(
            "21CSC201J",
            "Data Structures",
            4
        );

        // Course with lab
        Course c2 = new Course(
            "21CSC205L",
            "DSA Lab",
            3,
            1
        );

        System.out.println(c1.code + " total credits: " + c1.totalCredits());
        System.out.println(c2.code + " total credits: " + c2.totalCredits());
    }
}


// M4. Reference Copies and a Shared ID Card
class IdCard {
    String name;
    int booksIssued;

    IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class Main {
    public static void main(String[] args) {

        // Create one object
        IdCard ravi = new IdCard("Ravi", 0);

        // second variable points to the SAME object
        IdCard duplicate = ravi;

        // Change through second variable
        duplicate.booksIssued = 3;

        // Check through first variable
        System.out.println("Ravi's booksIssued: " + ravi.booksIssued);

        // Check whether both refer to same object
        System.out.println("ravi == duplicate: " + (ravi == duplicate));

        // Create a separate object with same values
        IdCard third = new IdCard("Ravi", 3);

        // Compare references
        System.out.println("ravi == third: " + (ravi == third));
    }
}



// M5. Instance vs Static: Splitting a Class Correctly
class Student {

    // Instance fields
    String name;
    int attendance;

    // Static fields - shared by all objects
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {
        System.out.println("College: " + collegeName);
        System.out.println("Number of students: " + studentCount);
    }
}

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("Ravi", 85);
        Student s2 = new Student("Anitha", 90);

        // Call static method using class name
        Student.printCollegeInfo();
    }
}

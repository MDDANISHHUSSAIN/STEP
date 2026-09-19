//M1. Library Book Cataloguing
class LibraryBook {
    String title;
    String isbn;

    // 2-argument constructor
    public LibraryBook(String title, String isbn) {
        this.title = title;

        if (isbn == null || isbn.isEmpty()) {
            this.isbn = "PENDING";
        } else {
            this.isbn = isbn;
        }
    }

    // 1-argument constructor
    // Chains to the above constructor
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    // Checks whether book is catalogued
    public boolean isCatalogued() {
        return !isbn.equals("PENDING");
    }

    public void printStatus() {
        System.out.println(title + " | " + isbn
                + " | Catalogued: " + isCatalogued());
    }
}

public class Main {
    public static void main(String[] args) {

        String[] titles = {
            "Clean Code",
            "Untitled Draft",
            "1984",
            "Notes"
        };

        String[] isbns = {
            "978-0132350884",
            "",
            "9780451524935",
            ""
        };

        // Single pass through both arrays
        for (int i = 0; i < titles.length; i++) {

            LibraryBook book;

            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }

            book.printStatus();
        }
    }
}


// M2. Payroll Batch Bonus Round
class Employee {
    String name;
    double salary;

    // Constructor
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Add bonus
    void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    // Print employee details
    void printSalary() {
        System.out.println(name + " - " + this.salary);
    }
}

public class Main {
    public static void main(String[] args) {

        Employee[] employees = {
            new Employee("Ravi", 30000),
            new Employee("Priya", 40000),
            new Employee("Arjun", 35000)
        };

        double bonus = 5000;

        // Single pass
        for (Employee e : employees) {
            e.raiseSalary(bonus);
            e.printSalary();
        }
    }
}


// M3. Late Fees — Skip the On-Time Accounts
class Account {

    String regNo;
    double totalFee;

    Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // final method - cannot be overridden
    final double calculateLateFee(int daysLate) {
        return totalFee * daysLate / 100;
    }

    // final method - cannot be overridden
    final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);

            System.out.println(regNo
                    + " | Total Fee: Rs " + totalFee
                    + " | Late Fee: Rs " + lateFee);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        String[] regNos = {
            "RA001", "RA002", "RA003", "RA004"
        };

        double[] totalFees = {
            200000, 150000, 180000, 220000
        };

        int[] daysLate = {
            10, 0, -2, 5
        };

        // Single pass through the whole batch
        for (int i = 0; i < regNos.length; i++) {

            Account account =
                new Account(regNos[i], totalFees[i]);

            account.printSummary(daysLate[i]);
        }
    }
}


// M4. One-Time College Setup, Many Students
class Account {

    String regNo;
    double totalFee;

    Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // final method - cannot be overridden
    final double calculateLateFee(int daysLate) {
        return totalFee * daysLate / 100;
    }

    // final method - cannot be overridden
    final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);

            System.out.println(regNo
                    + " | Total Fee: Rs " + totalFee
                    + " | Late Fee: Rs " + lateFee);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        String[] regNos = {
            "RA001", "RA002", "RA003", "RA004"
        };

        double[] totalFees = {
            200000, 150000, 180000, 220000
        };

        int[] daysLate = {
            10, 0, -2, 5
        };

        // Single pass through the whole batch
        for (int i = 0; i < regNos.length; i++) {

            Account account =
                new Account(regNos[i], totalFees[i]);

            account.printSummary(daysLate[i]);
        }
    }
}


// M5. Account Batch Payments
class FeeAccount {

    void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {

    @Override
    void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class Main {

    static int hostelCount = 0;
    static int dayScholarCount = 0;

    static void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {
            account.pay(amount);
            hostelCount++;
        } 
        else {
            account.pay(amount);
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;

        // Process the complete batch
        for (FeeAccount account : accounts) {
            processPayment(account, amount);
        }

        // Print counters only once after full batch
        System.out.println(
            "Hostel accounts processed: " + hostelCount +
            " | Day-scholar accounts processed: " + dayScholarCount
        );
    }
}
//M1. From Parallel Arrays to a Class — Library Inventory
class BookInventory {

    String title;
    String author;
    int copiesAvailable;

    // Constructor
    BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    // Instance method
    void printEntry() {
        System.out.println(title + " by " + author + " - "
                           + copiesAvailable + " copies available");
    }
}

public class Main {
    public static void main(String[] args) {

        // Array of BookInventory objects
        BookInventory[] books = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };

        // Print each book
        for (BookInventory book : books) {
            book.printEntry();
        }
    }
}

// M2. Encapsulated Payroll Account
class PayrollAccount {

    private double basicSalary;
    private double bonus;

    // Constructor
    public PayrollAccount(double basicSalary) {

        if (basicSalary < 0) {
            System.out.println("Warning: Negative salary not allowed. Starting with 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }

        bonus = 0;
    }

    // Add bonus
    public void creditBonus(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid bonus amount.");
        } else {
            bonus = bonus + amount;
            System.out.println("Bonus credited successfully.");
        }
    }

    // Deduct tax
    public void deductTax(double percent) {

        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percentage.");
        } else {
            basicSalary = basicSalary - (basicSalary * percent / 100);
            System.out.println("Tax deducted successfully.");
        }
    }

    // Read-only access
    public double getNetSalary() {
        return basicSalary + bonus;
    }
}

public class Main {

    public static void main(String[] args) {

        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);

        account.deductTax(10);

        System.out.println("Net Salary: " + account.getNetSalary());
    }
}


// M3. Overloaded Constructors for an Employee
class Employee {

    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // 3-argument constructor - Permanent employee
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // 2-argument constructor - Intern
    public Employee(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    // Print employee details
    public void printProfile() {
        System.out.println(empId + " | " + empName +
                " | Rs " + salary +
                " | Intern: " + isIntern);
    }
}

public class Main {
    public static void main(String[] args) {

        // Permanent employee
        Employee e1 = new Employee("E-101", "Divya", 65000);

        // Intern
        Employee e2 = new Employee("E-102", "Arjun");

        e1.printProfile();
        e2.printProfile();
    }
}

// M4. Reference Copies and a Shared Exam Hall Ticket
class HallTicket {

    String studentName;
    int seatNumber;

    // Constructor
    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

public class Main {
    public static void main(String[] args) {

        // First object
        HallTicket priya = new HallTicket("Priya", 0);

        // Reference copy - NOT a new object
        HallTicket copy = priya;

        // Change through second variable
        copy.seatNumber = 45;

        // Check through first variable
        System.out.println("Priya's seatNumber (via first variable): "
                           + priya.seatNumber);

        // Check whether both variables refer to same object
        System.out.println("copy == priya: " + (copy == priya));

        // Create a completely separate object
        HallTicket separate = new HallTicket("Priya", 45);

        // Check reference identity
        System.out.println("separate == priya: " + (separate == priya));
    }
}

// M5. Instance vs Static: Splitting an Employee Class Correctly
class Employee {

    // Instance fields
    String empName;
    double salary;

    // Static fields - shared by all employees
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    // Constructor
    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;

        employeeCount++;
    }

    // Static method
    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Main {
    public static void main(String[] args) {

        // Create 3 Employee objects
        Employee e1 = new Employee("Ravi", 50000);
        Employee e2 = new Employee("Priya", 60000);
        Employee e3 = new Employee("Arjun", 55000);

        // Call static method through class name
        Employee.printCompanyInfo();
    }
}

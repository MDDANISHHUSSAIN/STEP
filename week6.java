// Library Membership Foundation & Batch Enrollment Validator
class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return String.valueOf(rejected);
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}


// Three Branches of the Membership Tree
class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit,
                               String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit,
                         String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }
}

class MembershipTest {

    static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Honors Student Member";
        } 
        else if (member instanceof StudentMember) {
            return "Student Member";
        } 
        else if (member instanceof FacultyMember) {
            return "Faculty Member";
        } 
        else {
            return "General Member";
        }
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }
}


// The Student Discount & Fine Ledger
import java.util.Arrays;

class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    // Fine history
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Base fine logic
    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    // Defensive copy
    int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    // Total fine
    int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}


class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    // Student gets 50% discount
    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}


class Main {
    public static void main(String[] args) {

        StudentMember s =
            new StudentMember("STU5", 3, "CSE");

        s.chargeFine(100);

        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();

        history[0] = 999;

        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}


// The Weekly Circulation Report
class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    void displayInfo() {
        System.out.print("General | Books: " + booksBorrowed);
    }
}


class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    @Override
    void displayInfo() {
        System.out.print("Student | Course: " + course
                + " | Books: " + getBooksBorrowed());
    }
}


class Main {

    static String batchPrint(LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {

            // Polymorphic call
            member.displayInfo();

            // Add the same information to StringBuilder
            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                report.append("Student | Course: ")
                      .append(student.getCourse())
                      .append(" | Books: ")
                      .append(student.getBooksBorrowed())
                      .append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("] | ");
            } 
            else {
                report.append("General | Books: ")
                      .append(member.getBooksBorrowed())
                      .append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}



// Membership Numbers, Renewal Codes & the Nightly Circulation Audit
class LibraryMember {

    // Shared counter for all members
    private static int counter = 101;

    // Final member number
    final String memberNumber;

    protected int borrowLimit;
    protected int booksBorrowed;

    // Constructor
    public LibraryMember(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        // Assign unique number
        this.memberNumber = "LIB-" + counter;

        // Increment once per object
        counter++;
    }

    // No-argument borrowBook()
    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    // Overloaded borrowBook()
    void borrowBook(String genre) {
        // Reuse existing logic instead of duplicating it
        borrowBook();
    }

    // Check renewal code
    static boolean isValidRenewalCode(String code) {

        // Format: R + 2 digits + 1 uppercase letter
        // Example: R12A

        if (code == null || code.length() != 4) {
            return false;
        }

        // First character must be R
        if (code.charAt(0) != 'R') {
            return false;
        }

        // Second and third must be digits
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        // Fourth must be uppercase letter
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    // Number of members created
    static int getMembersEnrolled() {
        return counter - 101;
    }
}


// FacultyMember inherits LibraryMember
class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    // Override no-argument method
    @Override
    void borrowBook() {
        super.borrowBook();
    }

    // Overloaded version
    @Override
    void borrowBook(String genre) {
        super.borrowBook();
    }
}


public class Main {

    // Nightly audit
    static String processNightlyAudit(LibraryMember[] members) {

        int facultyCount = 0;
        int regularCount = 0;

        for (LibraryMember member : members) {

            // Never throw exception for null
            if (member == null) {
                continue;
            }

            // Use instanceof to identify FacultyMember
            if (member instanceof FacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return "Regular: " + regularCount +
               " | Faculty: " + facultyCount;
    }


    public static void main(String[] args) {

        // -------------------------------
        // 1. Member numbers
        // -------------------------------

        LibraryMember m1 = new LibraryMember(3);

        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());


        // -------------------------------
        // 2. More members
        // -------------------------------

        LibraryMember m2 = new LibraryMember(5);

        FacultyMember f1 =
            new FacultyMember(10, "CSE");

        System.out.println(m2.memberNumber);
        System.out.println(f1.memberNumber);

        System.out.println(LibraryMember.getMembersEnrolled());


        // -------------------------------
        // 3. Borrow books
        // -------------------------------

        m1.borrowBook();
        m1.borrowBook();

        // Overloaded method
        m1.borrowBook("Science");

        System.out.println("Books borrowed by m1: "
                           + m1.booksBorrowed);


        // -------------------------------
        // 4. Renewal codes
        // -------------------------------

        System.out.println(
            LibraryMember.isValidRenewalCode("R12A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("R1A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("R12a")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("X12A")
        );


        // -------------------------------
        // 5. Nightly audit
        // -------------------------------

        LibraryMember[] members = {
            m1,
            m2,
            f1,
            null
        };

        System.out.println(
            processNightlyAudit(members)
        );
    }
}
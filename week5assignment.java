// 1.Membership Field Reach Checker
class LibraryMember {

    // Only accessible inside LibraryMember
    private String membershipPin;

    // Accessible only within the same package
    String branchCode;

    // Accessible in same package
    // and in subclasses (cross-package subclass case is for Problem 2)
    protected double finesOwed;

    // Accessible from anywhere
    public String displayName;
}

class AccessChecker {

    // Checks whether a modifier is accessible in a given context
    static String classifyAccess(String fieldModifier, String accessorContext) {

        switch (fieldModifier) {

            case "private":
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }

            case "default":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }

            case "protected":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    // Summarize results for all four modifiers
    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private",
            "default",
            "protected",
            "public"
        };

        String result = "";

        for (String modifier : modifiers) {

            int allowed = 0;
            int denied = 0;

            // Check all attempts
            for (String[] attempt : attempts) {

                String fieldModifier = attempt[0];
                String context = attempt[1];

                if (fieldModifier.equals(modifier)) {

                    String answer =
                        classifyAccess(fieldModifier, context);

                    if (answer.equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (!result.equals("")) {
                result += " | ";
            }

            result += modifier + ": "
                   + allowed + " allowed / "
                   + denied + " denied";
        }

        return result;
    }
}

public class Main {

    public static void main(String[] args) {

        // Test 1
        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        // Test 2
        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        // All attempts
        String[][] attempts = {

            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},

            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},

            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},

            {"public", "DIFFERENT_PACKAGE"}
        };

        // Print summary
        System.out.println(
            AccessChecker.summarizeByModifier(attempts)
        );
    }
}

// 2.Reference Desk Subclass Reach
class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        // private
        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        // default
        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        // protected
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        // public
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    static String firstDeniedAttempt(String[][] attempts) {

        // Scan strictly from first to last
        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (result.equals("DENIED")) {

                return modifier
                    + " via "
                    + context
                    + " (attempt #"
                    + (i + 1)
                    + ")";
            }
        }

        return "None Denied";
    }
}


public class Main {

    public static void main(String[] args) {

        String[][] attempts = {

            {"public",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
            AccessChecker.firstDeniedAttempt(attempts)
        );
    }
}


//3.Book Copy Circulation Guard
public class Main {

    static class BookInventory {

        private int copiesTotal;
        private int copiesAvailable;

        // Constructor
        BookInventory(int copiesTotal) {

            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        // Check out one copy
        void checkOut() {

            if (copiesAvailable > 0) {

                copiesAvailable--;

            }
            // Otherwise do nothing
        }

        // Check in one copy
        void checkIn() {

            if (copiesAvailable < copiesTotal) {

                copiesAvailable++;

            }
            // Otherwise do nothing
        }

        // Getter
        int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {

        BookInventory b =
            new BookInventory(3);

        // Check out 4 times
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(
            b.getCopiesAvailable()
        );

        // Check in 4 times
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(
            b.getCopiesAvailable()
        );
    }
}


// 4.LibraryMember JavaBean & Security Answer Property
class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // Public no-argument constructor
    public LibraryMember() {
    }

    // Membership ID - write once
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium member
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Security Answer - write only
    public void setSecurityAnswer(String answer) {
        securityAnswer = answer.toLowerCase();
    }
}


// 5.Immutable Loan Receipt & Nightly Circulation Ledger
final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();   // defensive copy
    }

    public String[] getBookIds() {
        return bookIds.clone();           // defensive copy
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] newBooks = bookIds.clone();
        newBooks[index] = newId;

        return new LoanReceipt(memberId, newBooks);
    }
}


class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}


class CirculationLedger {

    static {
        System.out.println("Branch code initialized");
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            // Handle null
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            // Check subclass
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}
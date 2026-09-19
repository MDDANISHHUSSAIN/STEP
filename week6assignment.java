//Gym Membership Foundation & Batch Trial Sign-up Validator
class GymMember {

    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    // Constructor
    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    // Attend a session
    void attendSession() {
        sessionsAttended++;
    }

    // Getter
    int getSessionsAttended() {
        return sessionsAttended;
    }
}


// PremiumMember inherits GymMember
class PremiumMember extends GymMember {

    private String trainerName;

    // Constructor
    public PremiumMember(String memberId,
                         int monthlyFee,
                         String trainerName) {

        // Send common fields to parent
        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }
}


public class Main {

    // Batch sign-up
    static String signUpBatch(String[] memberIds, int monthlyFee) {

        int rejected = 0;

        // Try every ID
        for (String id : memberIds) {

            try {

                // Must attempt construction
                GymMember member =
                    new GymMember(id, monthlyFee);

            } catch (IllegalArgumentException e) {

                // Count rejected members
                rejected++;
            }
        }

        return String.valueOf(rejected);
    }


    public static void main(String[] args) {

        // --------------------------------
        // 1. Invalid member ID
        // --------------------------------

        try {

            GymMember g =
                new GymMember("GM1", 1000);

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }


        // --------------------------------
        // 2. Premium Member
        // --------------------------------

        PremiumMember p =
            new PremiumMember(
                "MEM01",
                2000,
                "Coach Riya"
            );

        p.attendSession();
        p.attendSession();

        System.out.println(
            p.getSessionsAttended()
        );


        // --------------------------------
        // 3. Batch sign-up
        // --------------------------------

        String[] ids = {
            "GM101",
            "AB",
            "MEM02",
            "   ",
            "GYM05"
        };

        System.out.println(
            signUpBatch(ids, 1500)
        );
    }
}


// Three Tiers of Gym Membership
class GymMember {

    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: "
            + sessionsAttended
        );
    }
}


// PremiumMember inherits GymMember
class PremiumMember extends GymMember {

    protected String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Premium Member | Trainer: "
            + trainerName
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


// EliteMember inherits PremiumMember
// Multilevel inheritance:
// GymMember -> PremiumMember -> EliteMember
class EliteMember extends PremiumMember {

    private String lockerNumber;

    public EliteMember(
            String memberId,
            int monthlyFee,
            String trainerName,
            String lockerNumber) {

        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Elite Member | Trainer: "
            + trainerName
            + " | Locker: "
            + lockerNumber
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


// GroupClassMember directly extends GymMember
// This is hierarchical inheritance.
class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Group Class Member | Class: "
            + className
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


public class Main {

    // Identify membership type using instanceof
    static String classifyGeneration(GymMember member) {

        if (member instanceof EliteMember) {
            return "Elite Member";
        }

        if (member instanceof PremiumMember) {
            return "Premium Member";
        }

        if (member instanceof GroupClassMember) {
            return "Group Class Member";
        }

        return "Standard Member";
    }


    // Add sessions from all types
    static int getTotalSessionsAttended(
            GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {

            // Do NOT check the type.
            // Just call the common method.
            total += member.getSessionsAttended();
        }

        return total;
    }


    public static void main(String[] args) {

        // Standard member
        GymMember m1 =
            new GymMember("MEM1", 1000);

        // Premium member
        PremiumMember m2 =
            new PremiumMember(
                "MEM2",
                2000,
                "Coach Riya"
            );

        // Elite member
        EliteMember m3 =
            new EliteMember(
                "MEM3",
                3000,
                "Coach Arjun",
                "L12"
            );

        // Group class member
        GroupClassMember m4 =
            new GroupClassMember(
                "MEM4",
                1500,
                "Zumba"
            );


        // Display information
        m1.displayInfo();
        m2.displayInfo();
        m3.displayInfo();
        m4.displayInfo();


        // Attend sessions
        m1.attendSession();

        m2.attendSession();
        m2.attendSession();

        m3.attendSession();
        m3.attendSession();
        m3.attendSession();

        m4.attendSession();


        // Mixed array
        GymMember[] members = {
            m1, m2, m3, m4
        };


        // classifyGeneration()
        System.out.println(
            classifyGeneration(m1)
        );

        System.out.println(
            classifyGeneration(m2)
        );

        System.out.println(
            classifyGeneration(m3)
        );

        System.out.println(
            classifyGeneration(m4)
        );


        // Total sessions
        System.out.println(
            "Total Sessions: "
            + getTotalSessionsAttended(members)
        );
    }
}


// The Premium Loyalty Discount & Late-Fee Ledger
import java.util.Arrays;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory;
    private int feeCount;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        lateFeeHistory = new int[10];
        feeCount = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    // Base class late-fee logic
    protected void chargeLateFee(int amount) {
        if (amount < 0) {
            return;
        }

        // Increase array size if required
        if (feeCount == lateFeeHistory.length) {
            lateFeeHistory = Arrays.copyOf(
                lateFeeHistory,
                lateFeeHistory.length * 2
            );
        }

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }

    // Defensive copy
    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;

        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    // Premium members get 50% discount
    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }

    public String getTrainerName() {
        return trainerName;
    }
}


public class Main {
    public static void main(String[] args) {

        PremiumMember p = new PremiumMember(
            "MEM5",
            2000,
            "Coach Riya"
        );

        p.chargeLateFee(200);

        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}



// The Monthly Attendance Announcer
class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    // Base display method
    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    // Method overriding
    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName
                + " | Sessions: " + sessionsAttended;
    }
}


public class Main {

    static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            // Polymorphic call
            result.append(member.displayInfo());

            // Downcasting only after instanceof check
            if (member instanceof PremiumMember) {

                PremiumMember premium = (PremiumMember) member;

                result.append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }


    public static void main(String[] args) {

        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}



// Membership Numbers, Referral Codes & the Weekly Check-in Settlement
class GymMember {

    // Shared counter
    private static int nextMembershipNumber = 2001;

    // Count of members created
    private static int membersEnrolled = 0;

    // Must never change after construction
    final String membershipNumber;

    protected int monthlyFee;
    protected int feesPaid;
    protected int checkIns;

    public GymMember(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
        this.checkIns = 0;

        // Assign unique number
        membershipNumber = "GYM-" + nextMembershipNumber;

        nextMembershipNumber++;
        membersEnrolled++;
    }

    // -------------------------------
    // Method Overloading - Version 1
    // -------------------------------
    public void payFee(int amount) {
        feesPaid += amount;
    }

    // -------------------------------
    // Method Overloading - Version 2
    // -------------------------------
    public void payFee(int amount, String mode) {
        // Reuse the first payFee() method
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    // Check-in
    public void checkIn() {
        checkIns++;
    }

    public int getCheckIns() {
        return checkIns;
    }

    // ------------------------------------
    // Referral code validation
    // Format: G + 2 digits + 1 uppercase
    // Example: G45B
    // ------------------------------------
    public static boolean isValidReferralCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        // First character must be G
        if (code.charAt(0) != 'G') {
            return false;
        }

        // Characters 1 and 2 must be digits
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        // Last character must be uppercase
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }
}


// =====================================================
// GROUP CLASS MEMBER
// =====================================================

class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}


// =====================================================
// MAIN CLASS
// =====================================================

public class Main {

    static String processWeeklyCheckIn(GymMember[] members) {

        int regularMembers = 0;
        int groupMembers = 0;
        int totalCheckIns = 0;

        for (GymMember member : members) {

            // Safely skip null entries
            if (member == null) {
                continue;
            }

            // Check whether it is GroupClassMember
            if (member instanceof GroupClassMember) {
                groupMembers++;
            } else {
                regularMembers++;
            }

            totalCheckIns += member.getCheckIns();
        }

        return "Regular Members: " + regularMembers
                + " | Group Class Members: " + groupMembers
                + " | Total Check-ins: " + totalCheckIns;
    }


    public static void main(String[] args) {

        // ---------------------------------
        // 1. Membership number
        // ---------------------------------

        GymMember m1 = new GymMember(1000);

        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());


        // ---------------------------------
        // 2. Referral code
        // ---------------------------------

        System.out.println(
            GymMember.isValidReferralCode("G45B")
        );

        System.out.println(
            GymMember.isValidReferralCode("G4XB")
        );


        // ---------------------------------
        // 3. Method overloading
        // ---------------------------------

        m1.payFee(500);

        m1.payFee(300, "UPI");

        System.out.println(m1.getFeesPaid());


        // ---------------------------------
        // 4. Group class member
        // ---------------------------------

        GroupClassMember g1 =
            new GroupClassMember(1500, "Zumba");

        g1.payFee(1000, "Card");

        g1.checkIn();
        g1.checkIn();
        g1.checkIn();


        // ---------------------------------
        // 5. Weekly check-in
        // ---------------------------------

        GymMember[] members = {
            m1,
            g1,
            null
        };

        m1.checkIn();
        m1.checkIn();

        System.out.println(
            processWeeklyCheckIn(members)
        );
    }
}
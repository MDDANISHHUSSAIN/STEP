//A1. Overloaded Constructors for Hackathon Registration
class Participant {

    String name;
    String teamName;
    boolean registered;

    // Constructor 1: name + team
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Constructor 2: only name
    // Constructor chaining
    public Participant(String name) {
        this(name, "Unassigned");
    }

    // Print participant details
    public void printStatus() {
        System.out.println(
            "Name: " + name +
            ", Team: " + teamName +
            ", Registered: " + registered
        );
    }
}

public class Main {

    public static void main(String[] args) {

        String[] names = {
            "Rahul",
            "Aman",
            "Priya",
            "Arjun"
        };

        String[] teamNames = {
            "Code Warriors",
            "",
            "Bug Slayers",
            ""
        };

        // Process every participant
        for (int i = 0; i < names.length; i++) {

            Participant p;

            if (teamNames[i].equals("")) {
                // No team → use one-argument constructor
                p = new Participant(names[i]);
            } 
            else {
                // Team available → use two-argument constructor
                p = new Participant(names[i], teamNames[i]);
            }

            p.printStatus();
        }
    }
}

// A2. this Keyword for Canteen Inventory — Batch Restock
class Item {

    String itemName;
    int stock;

    // Constructor
    Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    // Restock method
    void restock(int stock) {
        this.stock = this.stock + stock;
    }
}

public class Main {

    public static void main(String[] args) {

        // Array of 4 Item objects
        Item[] items = {
            new Item("Samosa", 15),
            new Item("Tea Powder", 40),
            new Item("Bread", 8),
            new Item("Biscuit Packs", 25)
        };

        // Restock every item by 20
        for (Item item : items) {

            item.restock(20);

            System.out.println(
                item.itemName + " | Final Stock: " + item.stock
            );
        }
    }
}

// A3. final Method — Parking Overstay Fine Calculator
class ParkingTicket {

    String vehicleNo;
    double ratePerMinute;

    // Constructor
    ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    // final method - cannot be overridden
    final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    // final method - cannot be overridden
    final void printReceipt(int overstayMinutes) {
        double fine = calculateFine(overstayMinutes);

        System.out.println(
            vehicleNo + " - Fine: " + fine
        );
    }
}

public class Main {

    public static void main(String[] args) {

        String[] vehicleNos = {
            "TN01AB1234",
            "TN02CD5678",
            "TN03EF9012",
            "TN04GH3456"
        };

        double[] rates = {
            2.0,
            3.0,
            1.5,
            2.5
        };

        int[] overstayMinutes = {
            30,
            0,
            -5,
            20
        };

        for (int i = 0; i < vehicleNos.length; i++) {

            ParkingTicket ticket =
                new ParkingTicket(vehicleNos[i], rates[i]);

            if (overstayMinutes[i] > 0) {

                ticket.printReceipt(overstayMinutes[i]);

            } else {

                System.out.println(
                    vehicleNos[i] + " - No fine, within allotted time"
                );
            }
        }
    }
}


// A4. Static Block — Library Membership Card Setup
class MembershipCard {

    // Static fields
    static String libraryName;
    static String validUntil;

    // Instance field
    String studentName;

    // Static block
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    // Constructor
    MembershipCard(String studentName) {
        this.studentName = studentName;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] students = {
            "Rahul",
            "Aman",
            "Priya",
            "Arjun",
            "Karan"
        };

        // Create a card for each student
        for (String name : students) {

            MembershipCard card = new MembershipCard(name);

            System.out.println(
                "Card created for " + card.studentName +
                " | Library: " + MembershipCard.libraryName +
                " | Valid Until: " + MembershipCard.validUntil
            );
        }
    }
}


// A5. instanceof Inside a Loop — Canteen Closing-Time Payment Dispatch
class Payment {

    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " + total
        );
    }
}

public class Main {

    static void processTransaction(Payment payment, double amount) {

        if (payment instanceof CardPayment) {

            CardPayment card = (CardPayment) payment;

            card.payWithProcessingFee(amount);

        } else {

            payment.pay(amount);
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100, 50, 200, 75, 120
        };

        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {

            processTransaction(payments[i], amounts[i]);

            if (payments[i] instanceof CardPayment) {
                totalCollected += amounts[i] * 1.02;
            } else {
                totalCollected += amounts[i];
            }
        }

        System.out.println(
            "Total Collected: Rs " + totalCollected
        );
    }
}
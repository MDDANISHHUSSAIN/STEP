//The Talking Toy Box
abstract class Toy {
    private static int counter = 1000;
    private final String toyId;

    // Constructor
    public Toy(String name) {
        toyId = "TOY-" + counter++;
    }

    // Abstract method
    public abstract String makeSound();

    // Getter
    public String getToyId() {
        return toyId;
    }
}


// ToyCar extends Toy
class ToyCar extends Toy {
    private String name;

    public ToyCar(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}


// ToyRobot extends Toy
class ToyRobot extends Toy {
    private String name;

    public ToyRobot(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}


// Main class
public class Main {
    public static void main(String[] args) {

        ToyCar c = new ToyCar("Speedster");

        System.out.println(c.makeSound());
        System.out.println(c.getToyId());


        ToyRobot r = new ToyRobot("Bolt");

        System.out.println(r.makeSound());
        System.out.println(r.getToyId());

        // This is NOT allowed:
        // Toy t = new Toy("Generic");
        // Error because Toy is abstract.
    }
}


// Warehouse Label Printer
interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}

public class Main {

    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {

        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");

        System.out.println(p.printLabel());
        System.out.println(i.printLabel());

        printAll(new Printable[]{p, i});
    }
}



// Orchestra Warm-Up Routine
abstract class Instrument {

    public abstract String play();
}


class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return "Strumming the strings";
    }
}


class Violin extends StringInstrument {

    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}


public class Main {

    public static void main(String[] args) {

        StringInstrument s = new StringInstrument();
        System.out.println(s.play());

        Violin v = new Violin();
        System.out.println(v.play());
    }
}



// Smart Kitchen Assistant
abstract class KitchenTool {

    private int speedLevel = 1;

    public abstract String prepare();

    // Getter
    public int getSpeedLevel() {
        return speedLevel;
    }

    // Setter
    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        } else {
            System.out.println("rejected, speed level stays " + this.speedLevel);
        }
    }
}


// Interface
interface Washable {
    String clean();
}


// Blender extends KitchenTool and implements Washable
class Blender extends KitchenTool implements Washable {

    public Blender() {
        super();
    }

    @Override
    public String prepare() {
        return "Blending food";
    }

    @Override
    public String clean() {
        return "Blender cleaned";
    }
}


// Main class
public class Main {

    public static void main(String[] args) {

        Blender b = new Blender();

        // Valid speed
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        // Invalid speed
        b.setSpeedLevel(9);

        // Prepare
        System.out.println(b.prepare());

        // Clean
        System.out.println(b.clean());
    }
}



// Package Drop-Off Log
abstract class DeliveryNote {

    // Abstract method
    public abstract String confirmDelivery();

    // Overloaded method
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}


class ParcelNote extends DeliveryNote {

    private String trackingId;

    public ParcelNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}


class LetterNote extends DeliveryNote {

    private String trackingId;

    public LetterNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}


public class Main {

    static void logAll(DeliveryNote[] notes) {

        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {

        ParcelNote p = new ParcelNote("TRK-1");

        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        LetterNote l = new LetterNote("LTR-5");

        System.out.println(l.confirmDelivery());
        System.out.println(l.confirmDelivery("A. Kumar"));

        DeliveryNote[] notes = {
            new ParcelNote("TRK-10"),
            new LetterNote("LTR-20")
        };

        logAll(notes);
    }
}
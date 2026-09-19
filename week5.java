//Movie Ticket Field Visibility Checker
class MovieTicket {

    private int seatNumber;
    String screenId;              // default
    protected double ticketPrice;
    public String movieTitle;
}


class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result =
                classifyAccess(attempt[0], attempt[1]);

            if (result.equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed +
               " | Denied: " + denied;
    }
}


// Subclass Ticket Access
static String classifyAccess(String fieldModifier, String accessorContext) {

    if (fieldModifier.equals("public")) {
        return "ALLOWED";
    }

    if (fieldModifier.equals("private")) {
        return "DENIED";
    }

    if (fieldModifier.equals("default")) {
        return "DENIED";
    }

    if (fieldModifier.equals("protected")) {

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
            return "DENIED";
        }

        // Same package and same class
        if (accessorContext.equals("SAME_CLASS") ||
            accessorContext.equals("SAME_PACKAGE")) {
            return "ALLOWED";
        }
    }

    return "DENIED";
}

//Seat Booking Encapsulation Guard
class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("Invalid seat count");
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    int getSeatsAvailable() {
        return seatsAvailable;
    }
}

// MovieBookingProfile JavaBean & OTP Property
class MovieBookingProfile {

    private String name;
    private boolean confirmed;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        // OTP is write-only, so don't store it
    }
}



// Immutable Booking Receipt & Nightly Settlement
final class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] copy = seatNumbers.clone();
        copy[index] = newSeat;

        return new BookingReceipt(bookingId, copy);
    }
}
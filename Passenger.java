package giams;

public class Passenger extends Person {
    private String passportNumber;
    private String expiryDate;
    private String destination;

    public Passenger(String name, String nationalId, String nationality, String passportNumber) {
        this(name, nationalId, nationality, passportNumber, "N/A", "Unknown");
    }

    public Passenger(String name, String nationalId, String nationality, String passportNumber, String expiryDate, String destination) {
        super(name, nationalId, nationality);
        this.passportNumber = passportNumber;
        this.expiryDate = expiryDate;
        this.destination = destination;
    }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(" | Passport: " + passportNumber + " | Exp: " + expiryDate + " | Target: " + destination);
    }
}
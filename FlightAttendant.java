package giams;

public class FlightAttendant extends Employee {
    private String languagesSpoken;
    private int flightCount;

    public FlightAttendant(String name, String nationalId, String nationality, double salary, String shift, String languagesSpoken, int flightCount) {
        super(name, nationalId, nationality, salary, shift);
        this.languagesSpoken = languagesSpoken;
        this.flightCount = flightCount;
    }

    public String getLanguagesSpoken() { return languagesSpoken; }
    public void setLanguagesSpoken(String languagesSpoken) { this.languagesSpoken = languagesSpoken; }

    public int getFlightCount() { return flightCount; }
    public void setFlightCount(int flightCount) { this.flightCount = flightCount; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(" | [Attendant] Langs: " + languagesSpoken + " | Flights: " + flightCount);
    }
}
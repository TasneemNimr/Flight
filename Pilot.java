package giams;

public class Pilot extends Employee {
    private int flightHours;
    private String licenseType;

    public Pilot(String name, String nationalId, String nationality, double salary, String shift, int flightHours, String licenseType) {
        super(name, nationalId, nationality, salary, shift);
        this.flightHours = flightHours;
        this.licenseType = licenseType;
    }

    public int getFlightHours() { return flightHours; }
    public void setFlightHours(int flightHours) { this.flightHours = flightHours; }

    public String getLicenseType() { return licenseType; }
    public void setLicenseType(String licenseType) { this.licenseType = licenseType; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(" | [Pilot] Hours: " + flightHours + " | License: " + licenseType);
    }
}
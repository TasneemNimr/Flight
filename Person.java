package giams;

public abstract class Person {
    private int systemId;
    private String name;
    private String nationalId;
    private String nationality;

    private static int idCounter = 1000;

    public Person(String name, String nationalId, String nationality) {
        this.systemId = idCounter++;
        this.name = name;
        this.nationalId = nationalId;
        this.nationality = nationality;
    }

    public int getSystemId() { return systemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public void displayInfo() {
        System.out.print("ID: " + systemId + " | Name: " + name + " | Nat: " + nationality);
    }
}
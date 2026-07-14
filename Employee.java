
package giams;

public class Employee extends Person {
    private double salary;
    private String shift;

    public Employee(String name, String nationalId, String nationality, double salary, String shift) {
        super(name, nationalId, nationality);
        this.salary = salary;
        this.shift = shift;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(" | Salary: $" + salary + " | Shift: " + shift);
    }
}
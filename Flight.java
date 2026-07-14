package giams;

import java.util.ArrayList;

public class Flight {
    private String flightId;
    private String destination;
    private int maxSeats;
    private Pilot responsiblePilot;
    private ArrayList<FlightAttendant> attendants;
    private ArrayList<Passenger> bookedPassengers;
    private FlightStatus status;

    private static int totalExecutedFlights = 0;

    public Flight(String destination, int maxSeats, Pilot responsiblePilot) {
        this("GZ-" + (int)(Math.random() * 9000 + 1000), destination, maxSeats, responsiblePilot);
    }

    public Flight(String flightId, String destination, int maxSeats, Pilot responsiblePilot) {
        this.flightId = flightId;
        this.destination = destination;
        this.maxSeats = maxSeats;
        this.responsiblePilot = responsiblePilot;
        this.attendants = new ArrayList<>();
        this.bookedPassengers = new ArrayList<>();
        this.status = FlightStatus.SCHEDULED;
    }

    public static int getTotalExecutedFlights() { return totalExecutedFlights; }

    public void addFlightAttendant(FlightAttendant attendant) {
        if (attendant != null) {
            this.attendants.add(attendant);
        }
    }

    public void bookPassenger(Passenger passenger) throws FlightFullException, DuplicatePassengerException {
        if (bookedPassengers.size() >= maxSeats) {
            throw new FlightFullException("خطأ: الرحلة " + flightId + " ممتلئة بالكامل! السعة القصوى: " + maxSeats);
        }
        for (Passenger p : bookedPassengers) {
            if (p.getNationalId().equals(passenger.getNationalId())) {
                throw new DuplicatePassengerException("خطأ: المسافر " + passenger.getName() + " محجوز بالفعل في هذه الرحلة.");
            }
        }
        bookedPassengers.add(passenger);
    }

    public void changeStatus(FlightStatus newStatus) throws InvalidFlightStatusException {
        if (this.status == FlightStatus.DEPARTED || this.status == FlightStatus.CANCELLED) {
            throw new InvalidFlightStatusException("خطأ: لا يمكن تعديل حالة رحلة منتهية أو ملغاة (" + flightId + ").");
        }
        if (this.status == FlightStatus.BOARDING && newStatus == FlightStatus.SCHEDULED) {
            throw new InvalidFlightStatusException("خطأ منطقي: لا يمكن الرجوع لحالة الجدولة بعد بدء صعود الركاب.");
        }

        this.status = newStatus;

        if (this.status == FlightStatus.DEPARTED) {
            totalExecutedFlights++;
        }
    }

    public String getFlightId() { return flightId; }
    public String getDestination() { return destination; }
    public FlightStatus getStatus() { return status; }
    public int getPassengerCount() { return bookedPassengers.size(); }
    public Pilot getResponsiblePilot() { return responsiblePilot; }
    public ArrayList<FlightAttendant> getAttendants() { return attendants; }
    public ArrayList<Passenger> getBookedPassengers() { return bookedPassengers; }
}
# Flight
An OOP-based Java application designed to digitize airport operations, managing passengers, crew, and flights with robust file handling and custom exception structures.
# Gaza International Airport Management System (GIAMS)

Gaza International Airport Management System is a software project developed in Java to practically apply Object-Oriented Programming (OOP) concepts in a comprehensive and integrated manner. The system aims to replace the legacy paper-based operations at the airport with a flexible, modern, and secure digital system that manages flights, operating crews, and passengers.

## Applied Programming Concepts
- **Inheritance & Polymorphism:** Designed a two-level inheritance hierarchy containing a base class (Person), which branches into employees (Employee) and passengers (Passenger). The Employee class further branches into pilots (Pilot) and flight attendants (FlightAttendant). A polymorphic array of the base type is utilized to generate a comprehensive report using a single loop, dynamically distinguishing between the different objects.
- **Full Encapsulation:** Protected all sensitive object data by declaring variables as private and restricting access and modification exclusively through secure accessors and mutators (Getters & Setters).
- **Custom Exception Handling:** Created custom exceptions to safely handle operational errors, such as exceeding flight capacity, duplicate passenger bookings, or logical violations in flight status updates.
- **File I/O:** Programmed self-reading mechanisms to load pre-registered passenger data from external files upon system startup, and automated exporting of flight logs and daily comprehensive reports upon system shutdown.

## Class Structure & Relationships
- **Person (Superclass):** Contains the basic information for every person (name, national ID, nationality, and an auto-generated system ID).
  - **Employee (Subclass):** Inherits from Person and adds salary and shift details.
    - **Pilot (Sub-Subclass):** Inherits from Employee and adds flight hours and license type.
    - **FlightAttendant (Sub-Subclass):** Inherits from Employee and adds languages spoken and flight count.
  - **Passenger (Subclass):** Inherits from Person and adds passport number, passport expiry date, and destination.
- **Flight:** An independent class representing a flight. It is associated with a responsible pilot, at least two flight attendants, and a list of booked passengers, while dynamically tracking the flight's status and capacity.
- **Main:** The primary execution class responsible for bootstrapping the system, managing reservations, handling exceptions, and managing all file reading and writing operations.

## Operation & Output Files
Upon running the main class `Main.java`:
1. The system searches for `passengers.txt` to read passenger data. If the file is missing, it automatically generates a default file with mock data to ensure seamless execution.
2. Booking operations and flight status transitions are simulated with active error-detection mechanisms.
3. The system generates and saves two text files in the root directory:
   - `flights_log.txt`: Contains a detailed log of current flights, their statuses, and passenger counts.
   - `daily_report.txt`: Contains a comprehensive daily administrative report for all registered individuals in the system.

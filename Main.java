package giams;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Flight> flightList = new ArrayList<>();
    private static Person[] registryData = new Person[100];
    private static int registryCount = 0;

    public static void main(String[] args) {
        System.out.println("=== نظام إدارة مطار غزة الدولي (GIAMS) ===");

        loadPassengersFromFile("passengers.txt");

        Pilot pilot1 = new Pilot("Capt. Ahmed", "ID991", "Palestinian", 5000, "Morning", 1200, "ATPL");
        FlightAttendant attendant1 = new FlightAttendant("Sami", "ID441", "Egyptian", 2000, "Morning", "Arabic, English", 150);
        FlightAttendant attendant2 = new FlightAttendant("Mona", "ID442", "Palestinian", 2200, "Morning", "Arabic, French", 180);

        addToRegistry(pilot1);
        addToRegistry(attendant1);
        addToRegistry(attendant2);

        Flight flight1 = new Flight("Cairo International", 2, pilot1);
        flight1.addFlightAttendant(attendant1);
        flight1.addFlightAttendant(attendant2);
        flightList.add(flight1);

        System.out.println("\n--- معالجة عمليات الحجز وتحديثات الحالات ---");

        for (int i = 0; i < registryCount; i++) {
            if (registryData[i] instanceof Passenger) {
                Passenger p = (Passenger) registryData[i];
                try {
                    flight1.bookPassenger(p);
                    System.out.println("تم الحجز للمسافر: " + p.getName());
                } catch (FlightFullException | DuplicatePassengerException e) {
                    System.out.println("[تنبيه نظام الحجز] -> " + e.getMessage());
                }
            }
        }

        try {
            flight1.changeStatus(FlightStatus.BOARDING);
            flight1.changeStatus(FlightStatus.SCHEDULED);
        } catch (InvalidFlightStatusException e) {
            System.out.println("[تنبيه نظام الرحلات] -> " + e.getMessage());
        }

        try {
            flight1.changeStatus(FlightStatus.DEPARTED);
        } catch (InvalidFlightStatusException e) {
            System.out.println(e.getMessage());
        }

        // 1. طباعة التقرير الشامل على شاشة الكونسول
        generateDailyReport();

        // 2. حفظ التقرير اليومي الشامل في ملف نصي خارجي بناءً على طلب الدكتورة
        saveDailyReportToFile("daily_report.txt");

        // 3. حفظ سجل حركة الرحلات المعتاد للمشروع
        saveFlightsLog("flights_log.txt");

        System.out.println("\n=== تم إغلاق النظام بنجاح وحفظ سجل الحركة والتقارير ===");
    }

    private static void addToRegistry(Person p) {
        if (registryCount < registryData.length) {
            registryData[registryCount++] = p;
        }
    }

    // ميثود العرض على الشاشة (الكونسول)
    public static void generateDailyReport() {
        System.out.println("\n==================================================");
        System.out.println("              التقرير اليومي العام للمطار              ");
        System.out.println("==================================================");

        for (int i = 0; i < registryCount; i++) {
            Person p = registryData[i];

            p.displayInfo();

            if (p instanceof Pilot) {
                System.out.print(" -> [الحالة الإدارية: طيار معتمد ومجدول للرحلة]");
            } else if (p instanceof Passenger) {
                System.out.print(" -> [الحالة الإدارية: فحص وثائق السفر مكتمل]");
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------");
        System.out.println("إجمالي عدد الرحلات المنفذة الناجحة اليوم: " + Flight.getTotalExecutedFlights());
        System.out.println("==================================================");
    }

    // الميثود الجديدة المخصصة لحفظ التقرير الشامل في ملف نصي (.txt) لقاعدة البيانات اليومية
    public static void saveDailyReportToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("==================================================");
            writer.println("       سجل التقرير اليومي المكتوب للمسافرين والموظفين     ");
            writer.println("==================================================");

            // Loop موحد يمر على مصفوفة الأب ويكتب التفاصيل داخل الملف النصي
            for (int i = 0; i < registryCount; i++) {
                Person p = registryData[i];

                // تجميع البيانات الأساسية المشتركة عبر الـ Getters الآمنة
                String record = "ID: " + p.getSystemId() + " | Name: " + p.getName() + " | Nationality: " + p.getNationality();

                // استخدام instanceof لمعرفة نوع الكائن وكتابة تفاصيله الفرعية بدقة داخل الملف
                if (p instanceof Pilot) {
                    Pilot pilot = (Pilot) p;
                    record += " | Type: Pilot | Hours: " + pilot.getFlightHours() + " | License: " + pilot.getLicenseType();
                } else if (p instanceof FlightAttendant) {
                    FlightAttendant attendant = (FlightAttendant) p;
                    record += " | Type: Attendant | Languages: " + attendant.getLanguagesSpoken() + " | Flights: " + attendant.getFlightCount();
                } else if (p instanceof Passenger) {
                    Passenger passenger = (Passenger) p;
                    record += " | Type: Passenger | Passport: " + passenger.getPassportNumber() + " | Expiry: " + passenger.getExpiryDate() + " | Destination: " + passenger.getDestination();
                }

                // كتابة السطر كاملاً في الملف النصي
                writer.println(record);
            }

            writer.println("--------------------------------------------------");
            writer.println("إجمالي عدد الرحلات المنفذة والناجحة اليوم بالكامل: " + Flight.getTotalExecutedFlights());
            writer.println("==================================================");

            System.out.println(">> تم تصدير وحفظ التقرير النصي اليومي الشامل في ملف: " + filename);
        } catch (IOException e) {
            System.out.println("خطأ أثناء محاولة كتابة ملف التقرير اليومي: " + e.getMessage());
        }
    }

    public static void loadPassengersFromFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println("Khaled Ali|ID101|Palestinian|PS98765|2030-12-12|Cairo");
                pw.println("Youssef Amra|ID102|Jordanian|JO12345|2029-05-10|Amman");
                pw.println("Fatima Hassan|ID103|Palestinian|PS55443|2031-01-01|Cairo");
            } catch (IOException e) {
                System.out.println("تعذر إنشاء ملف افتراضي.");
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split("\\|");
                if (tokens.length == 6) {
                    Passenger passenger = new Passenger(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                    addToRegistry(passenger);
                }
            }
            System.out.println(">> تم تحميل بيانات المسافرين المسجلين مسبقاً من الملف بنجاح.");
        } catch (FileNotFoundException e) {
            System.out.println("خطأ: لم يتم العثور على الملف المحدد: " + e.getMessage());
        }
    }

    public static void saveFlightsLog(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Flight f : flightList) {
                writer.println(f.getFlightId() + " | " + f.getDestination() + " | " + f.getStatus() + " | Passengers: " + f.getPassengerCount());
            }
            System.out.println(">> تم حفظ سجل الرحلات الحالي في ملف: " + filename);
        } catch (IOException e) {
            System.out.println("خطأ أثناء محاولة حفظ سجلات الطيران: " + e.getMessage());
        }
    }
}
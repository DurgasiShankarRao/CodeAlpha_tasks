import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    static class Room {
        String type;
        double price;
        boolean isAvailable;

        Room(String type, double price) {
            this.type = type;
            this.price = price;
            this.isAvailable = true;
        }
    }

    static class Booking {
        String customerName;
        Room room;

        Booking(String customerName, Room room) {
            this.customerName = customerName;
            this.room = room;
        }

        void displayBooking() {
            System.out.println("✅ Booking Confirmed:");
            System.out.println("Customer: " + customerName);
            System.out.println("Room Type: " + room.type);
            System.out.println("Price: ₹" + room.price);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Room list
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Single", 1500));
        rooms.add(new Room("Double", 2500));
        rooms.add(new Room("Deluxe", 4000));
        rooms.add(new Room("AC", 6000));

        ArrayList<Booking> bookings = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.println("\n🏨 Available Rooms:");
                    int index = 1;
                    for (Room room : rooms) {
                        if (room.isAvailable) {
                            System.out.println(index + ". " + room.type + " - ₹" + room.price);
                        }
                        index++;
                    }
                }

                case 2 -> {
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.println("Select a room type to book:");
                    int i = 1;
                    for (Room room : rooms) {
                        System.out.println(i + ". " + room.type + " - ₹" + room.price + (room.isAvailable ? " (Available)" : " (Booked)"));
                        i++;
                    }

                    System.out.print("Enter room number to book: ");
                    int roomChoice = scanner.nextInt();

                    if (roomChoice < 1 || roomChoice > rooms.size()) {
                        System.out.println("❌ Invalid choice.");
                        break;
                    }

                    Room selectedRoom = rooms.get(roomChoice - 1);
                    if (!selectedRoom.isAvailable) {
                        System.out.println("❌ Sorry, that room is already booked.");
                        break;
                    }

                    selectedRoom.isAvailable = false;
                    Booking booking = new Booking(name, selectedRoom);
                    bookings.add(booking);

                    System.out.println("💳 Processing payment...");
                    System.out.println("✅ Payment Successful!");
                    booking.displayBooking();
                }

                case 3 -> {
                    System.out.println("\n📋 Booking Details:");
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings made yet.");
                    } else {
                        for (Booking b : bookings) {
                            b.displayBooking();
                            System.out.println("---------------------------");
                        }
                    }
                }

                case 4 -> System.out.println("👋 Thank you for using the Hotel Reservation System!");

                default -> System.out.println("❌ Invalid option. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

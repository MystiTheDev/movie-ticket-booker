package reservation.client;
import reservation.server.ReservationServer;
import java.util.*;

public class TheatreAdmin {
    public static void main(String args[])
    {
        ReservationServer server = new ReservationServer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Theatre Admin Panel v0.1!");
        System.out.println("1. Reset Number of available seats in Theatre");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                //Admin command to reset number of seats
                System.out.println("Current amount of seats: " +server.readSeatsFile());
                System.out.println("Enter number of seats to be reset to: ");
                int seat = sc.nextInt();
                server.resetNoOfAvailableSeats(seat);
                System.out.println("Seats have been reset!!");
                System.out.println("New current amount of seats: " +server.readSeatsFile());
                break;

            
            default:
                System.out.println("Invalid choice!!");
        
    }

    sc.close();
}
}

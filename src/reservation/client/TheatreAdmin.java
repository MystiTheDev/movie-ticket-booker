package reservation.client;
import reservation.server.ReservationServer;
import java.util.stream.*;
import java.util.*;

public class TheatreAdmin {
    public static void main(String args[])
    {
        ReservationServer server = new ReservationServer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Checking if a newer version is released...");
             printMsgWithProgressBar("Loading", 30, 500);
             System.out.println();
             
             int random = (int)(Math.random() *10) + 1;
             if(random % 2 == 0)
             {
                System.out.println("You are up to date!!");
             }
             else{
                printMsgWithProgressBar("Updating", 30, 900);
                System.out.println();
             }
        System.out.println("Welcome to Theatre Admin Panel v1.0!!");
        System.out.println("What would you like to do today??");
        System.out.println("1. Reset Number of available seats in Theatre");
        System.out.println("2. Add a new movie [can add only 4 movies at once]");
        int choice = sc.nextInt();

        switch(choice)
        {
            case 1: //Admin command to reset number of seats
                System.out.println("Current amount of seats: " +server.readSeatsFile());
                System.out.println("Enter number of seats to be reset to: ");
                int seat = sc.nextInt();
                server.resetNoOfAvailableSeats(seat);
                System.out.println("Seats have been reset!!");
                System.out.println("New current amount of seats: " +server.readSeatsFile());
                break;

            case 2: //Admin command to add a new movie
                server.addNewMovie();
                System.out.println("New movie list is: ");
                server.displayMovieName();
                break;
                
            default:
                System.out.println("Invalid choice!!");
        
    }

    sc.close();
}

public static void printMsgWithProgressBar(String message, int length, long timeInterval)
    {
        char incomplete = '░'; // U+2591 Unicode Character
        char complete = '█'; // U+2588 Unicode Character
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
        System.out.println(message);
        for(int i = 0; i < length; i++)
        {
            builder.replace(i,i+1,String.valueOf(complete));
            String progressBar = "\r"+builder;
            System.out.print(progressBar);
            try
            {
                Thread.sleep(timeInterval);
            }
            catch (InterruptedException ignored)
            {

            }
        }
    }
}

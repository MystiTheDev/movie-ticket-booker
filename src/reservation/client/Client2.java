package reservation.client;
import reservation.server.ReservationServer;
import java.util.*;

public class Client2 {
    public static void main(String args[])
    {
        ReservationServer server = new ReservationServer();
        String userName, emailId;
        long mobileNumber;

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello user!");
        System.out.println("You need to login to continue!");

        System.out.print("Enter your name: ");
        userName = sc.nextLine();

        System.out.print("Enter your email ID: ");
        emailId = sc.nextLine();

        System.out.print("Enter your mobile number: ");
        mobileNumber = sc.nextLong();
        sc.nextLine(); // Consume newline

        String[] movies = server.getMovieName();
        String[] offer = server.getOffers();

        server.setName(userName);
        server.setEmailId(emailId);
        server.setMobileNo(mobileNumber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        System.out.println("\nWelcome, " + userName + "!\n");

        // Show running movies
        System.out.println("The shows that are running are:");
        for (String movie : movies) {
            System.out.println("- " + movie);
        }

        // Show offers
        System.out.println("\nToday's offers:");
        for (String offers : offer) {
            System.out.println(offers);
        }
        


        // Menu
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View movie descriptions");
            System.out.println("2. Book a ticket for a movie");
            System.out.println("3. Exit program");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch(choice)
            {
                case 1:
                    server.viewMovieDetails();

                case 2:
                    server.bookTicket();

                case 3:
                    System.out.println("Exiting the program...");
                    System.out.println("Thank you for using my app!");


            }
            sc.close();
    }
}
}

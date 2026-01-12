package reservation.client;
import reservation.server.Movie;
import reservation.server.ReservationServer;
import reservation.server.Movie;
import java.util.*;
import java.util.stream.Stream;


public class Client1 {
    public static void main(String args[])
    {
        ReservationServer server = new ReservationServer();
        TheatreAdmin loader = new TheatreAdmin();
        String userName, emailId;
        long mobileNumber;

        Scanner sc = new Scanner(System.in);

        printMsgWithProgressBar("Loading", 30, 250);
        System.out.println();

        System.out.println("Hello user!");
        System.out.println("You need to login to continue!");

        System.out.print("Enter your name: ");
        userName = sc.nextLine();

        System.out.print("Enter your email ID: ");
        emailId = sc.nextLine();

        System.out.print("Enter your mobile number: ");
        mobileNumber = sc.nextLong();
        sc.nextLine(); // Consume newline

        Movie[] movies = server.getMovies();
        /**for(int i = 0; i<4; i++)
            System.out.println(movies[i] + " ");**/

        server.setName(userName);
        server.setEmailId(emailId);
        server.setMobileNo(mobileNumber);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        System.out.println("\nWelcome, " + userName + "!\n");

        // Show running movies
        System.out.println("The shows that are running are:");
        for (Movie movie : movies) {
            System.out.println("- " + movie);
        }
        
        // Menu
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View movie descriptions");
            System.out.println("2. Book a ticket for a movie");
            System.out.println("3. Exit program");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            //Choices
            switch(choice)
            {
                case 1:
                    server.viewMovieDetails(); //Shows the user the movie details
                    break; 

                case 2:
                    server.bookTicket(); //Takes the user to the booking ticket process
                    break;

                case 3:
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e)
                    {
                    }
                    System.out.println("Exiting the program...");
                    System.out.println("Thank you for using my app!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
            
    }
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

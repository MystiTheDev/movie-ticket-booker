package reservation.server;

import java.util.Scanner;
import java.io.*;

public class ReservationServer {

    final int TOTAL_SEATS = 100;
    final double TICKET_PRICE = 1000.0;
    final double REFRESHMENT_PRICE = 200.0;
    final double OFFER_DISCOUNT = 20.0;
    

    Scanner sc = new Scanner(System.in);
    int availableSeats = TOTAL_SEATS;

    String[] movieName = {"A Minecraft Movie", "Thunderbolts", "Fast and Furious", "Saiyaara", "Home Alone"};
    String[] movieGenre = {"Comedy, Animated", "Action, Violence", "Action, Racing", "Romantic", "Terror"};
    String[] movieDescription = {"A Beautiful Movie", "Fighting movie", "A good racing movie","Some love movie idk lol", "Alone boiii"};
    String[] offers = {"Flat 20Rs off on all movies!! [OFFER20]"};
    String[] timings = {"8:30", "9:30", "10:30"};

    String name;
    String emailId;
    long mobileNo;

    //Returns the movie name to the client
    public String[] getMovieName() 
    {
        return movieName;
    }

    //Returns the offers to the client
    public String[] getOffers() 
    {
        return offers;
    }

    //Saves the name from the client
    public void setName(String name) 
    {
        this.name = name;
    }

    //Saves the emailId from the client
    public void setEmailId(String email) 
    {
        emailId = email;
    }

    //Saves the mobileNumber from the client
    public void setMobileNo(long mobile)
    {
        mobileNo = mobile;
    }

    //Method to view the movie descriptions
    public void viewMovieDetails() {
        System.out.println("Which movie would you like to view?");
        for (String movie : movieName) {
            System.out.println("- " + movie);
        }

        String selectedMovie = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < movieName.length; i++) {
            if (selectedMovie.equalsIgnoreCase(movieName[i])) {
                found = true;
                System.out.println("\nMovie Name: " + movieName[i]);
                System.out.println("Description: " + movieDescription[i]);
                System.out.println("Genre: " + movieGenre[i]);

                System.out.print("\nWould you like to book a ticket for this movie? (Y/N): ");
                char ch = sc.next().charAt(0);
                sc.nextLine(); // consume newline

                if (ch == 'Y' || ch == 'y') {
                    bookTicketProcess(movieName[i]);
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Movie does not exist!");
        }
    }


    //Method to book the ticket
    public void bookTicket() {

        System.out.println("Which movie would you like to book?");
        for (String movie : movieName) {
            System.out.println("- " + movie);
        }

        String selectedMovie = sc.nextLine();

        boolean found = false;
        for (String name : movieName) {
            if (selectedMovie.equalsIgnoreCase(name)) {
                found = true;
                bookTicketProcess(name);
                break;
            }
        }

        if (!found) {
            System.out.println("Movie not found!");
        }
    }
    // Create the seats into a file
    public void createSeatsFile(int seat)
    {
        try{
            String theatreDetailsFile = "theatre_details.txt";
            //Check if directory exists. Otherwise, create a new directory
            File directory = new File("./theatreInfo");
            if(directory.mkdir())
            {
                //System.out.println("Path exists!");
            }
            
            File file = new File("./theatreInfo", theatreDetailsFile);
            PrintWriter print = new PrintWriter(new FileWriter(file));

            print.println("No of seats booked :" + seat);
            print.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //Read the seats from the file
    public int readSeatsFile()
    {
        File fileReader =  new File("./theatreInfo/theatre_details.txt");
        int availableseats = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileReader))) {
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            String totalSeatNum = line.substring(line.indexOf(':')+1);
            //System.out.println("File read value: "+totalSeatNum);
            availableseats = Integer.parseInt(totalSeatNum.trim());
        }
    } catch (IOException e) {
        System.err.println("Error reading the file: " + e.getMessage());
    }
    return availableseats;
}

    //Method to start the process
    public void bookTicketProcess(String movie) {
        System.out.println("\nThe movie \"" + movie + "\" has the following timings:");
        for (String time : timings) {
            System.out.print(time + " ");
        }
        System.out.println();

        System.out.print("Choose your preferred timing: ");
        String chosenTime = sc.nextLine();

        boolean validTime = false;
        for (String t : timings) {
            if (chosenTime.equalsIgnoreCase(t)) {
                validTime = true;
                break;
            }
        }

        if (!validTime) {
            System.out.println("Invalid timing selected.");
            return;
        }

        int leftSeats = readSeatsFile();
        System.out.println("\nThere are a total of " + leftSeats + " seats available.");
        System.out.println("Each ticket costs Rs." + TICKET_PRICE);

        System.out.print("How many seats would you like to book? ");
        int seats = sc.nextInt();
        sc.nextLine(); // consume newline

        

        if (seats >= leftSeats) {
            System.out.println("Not enough seats available.");
            return;
        }
        else{
            leftSeats -= seats;
        }
 
        double totalAmount = TICKET_PRICE * seats;
        

        //Create a file with the remaining seats available
        createSeatsFile(leftSeats);


        System.out.print("Would you like refreshments for Rs." + REFRESHMENT_PRICE + "? (Y/N): ");
        char ref = sc.next().charAt(0);
        sc.nextLine(); // consume newline

        boolean refreshments = false;
        if (ref == 'Y' || ref == 'y') {
            totalAmount += REFRESHMENT_PRICE;
            totalAmount -= OFFER_DISCOUNT;
            refreshments = true;
        }
        else
        {
            totalAmount -= OFFER_DISCOUNT;

        }

        System.out.println("\nPreparing your ticket...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        System.out.println("\nYour ticket is ready!");
        System.out.println("Movie\t\tSeats\tRefreshments\tDiscount\tTotal Price");
        System.out.println(movie + "\t" + seats + "\t" + ((ref == 'Y' || ref == 'y') ? "Yes" : "No") + "\t\tRs." + OFFER_DISCOUNT + "\t\tRs."+totalAmount);
        System.out.println("Do you want to print a receipt for this? Y/N");
        char receiptOption = sc.next().charAt(0);
        if(receiptOption == 'Y' || receiptOption == 'y')
        {
            //Calls the saveReceipt to save the receipt
            saveReceipt(emailId, mobileNo, name, movie, chosenTime, seats, refreshments, totalAmount);
            System.out.println("Thank you for booking with us!!!");
            System.exit(0);
        }
        else
        {
            System.out.println("Thank you for booking with us!!");
            System.exit(0);
        }

    }

    //Method to save the receipt
    public void saveReceipt(String email, long num, String name, String movie, String time, int seats, boolean wantsRefreshments, double total)
    {
        try {
            String fileName = "receipt_"+name+"_"+emailId.replace("[^a-zA-Z0-9]", "_") +".txt";
            //Check if directory exists. Otherwise, create a new directory
            File directory = new File("./receipts");
            if(directory.mkdir())
            {
                System.out.println("Path exists!");
            }


            //File file = new File("E:/java_programs_11th_std/Reservation_24_07_2025/src/reservation/bin/reservation/receipts", fileName);
            File file = new File("./receipts", fileName);
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println("-----------------------------MOVIE TICKET-----------------------------");
            out.println("----------------------------------------------------------------------");
            out.println("Name: " +name);
            out.println("Email: "+email);
            out.println("Movie chose: " +movie);
            out.println("Mobile Number: " +num);
            out.println("Timing chosen: " +time);
            out.println("Seats booked: " +seats);
            out.println("Refreshments: " +(wantsRefreshments?"Yes":"No"));
            out.println("Discount: " +OFFER_DISCOUNT);
            out.println("Total bill: " +total);
            out.println("------------------------------CUT HERE---------------------------------");
            out.println("Thank you for booking with us!");
            out.close();
                System.out.println("Receipt has been saved as: "+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving receipt");
        }
    }

    //Admin Commands 
    public void resetNoOfAvailableSeats(int seats)
    {
        try{
            String theatreDetailsFile = "theatre_details.txt";
            //Check if directory exists. Otherwise, create a new directory
            File directory = new File("./theatreInfo");
            if(directory.mkdir())
            {
                //System.out.println("Path exists!");
            }
            
            File file = new File("./theatreInfo", theatreDetailsFile);
            PrintWriter print = new PrintWriter(new FileWriter(file));

            print.println("No of seats booked :" + seats);
            print.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

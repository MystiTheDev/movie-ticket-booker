package reservation.server;

import java.util.Scanner;
import java.io.*;
import java.nio.file.Paths;

public class ReservationServer {

    final int TOTAL_SEATS = 100;
    final double TICKET_PRICE = 1000.0;
    final double REFRESHMENT_PRICE = 200.0;
    final double OFFER_DISCOUNT = 20.0;

    Scanner sc = new Scanner(System.in);
    int availableSeats = TOTAL_SEATS;

    /**
     * String[] movieName = {"A Minecraft Movie", "Thunderbolts", "Fast and
     * Furious", "Saiyaara", "Home Alone"};
     * String[] movieGenre = {"Comedy, Animated", "Action, Violence", "Action,
     * Racing", "Romantic", "Terror"};
     * String[] movieDescription = {"A Beautiful Movie", "Fighting movie", "A good
     * racing movie","Some love movie idk lol", "Alone boiii"};
     * String[] offers = {"Flat 20Rs off on all movies!! [OFFER20]"};
     * String[] timings = {"8:30", "9:30", "10:30"};
     **/
    Movie movies[] = deSerializeMovies();
    String movieName[] = new String[4];
    String movieGenre[] = new String[4];
    String movieDescription[] = new String[4];
    String timings[] = new String[4];
    String offers[] = new String[2];

    String name;
    String emailId;
    long mobileNo;

    // Default constructor
    public ReservationServer() {
        deSerializeMovies();
    }

    private void serializeMovies(Movie[] movies) {
        try {
            // String fileName = "movieDetails.ser";
            // Check if directory exists. Otherwise, create a new directory
            File directory = new File("./data");
            if (directory.mkdir()) {
                System.out.println("Path exists!");
            }

            // Print a sample movie name
            System.out.println("serializeMovies method value: " + movies[0]);

            try (FileOutputStream fos = new FileOutputStream("./data/movieDetails.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(movies);
                System.out.println("Movie objects serialized successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving receipt");
        }
    }

    private Movie[] deSerializeMovies() {
        Movie[] movies = null;

        try {
            File directory = new File("./data");
            if (directory.mkdir()) {
                System.out.println("Path exists!");
            }

            // Checking for file existence
            String fileName = "./data/movieDetails.ser";
            File file = new File(fileName);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream("./data/movieDetails.ser");
                        ObjectInputStream ois = new ObjectInputStream(fis)) {
                    movies = (Movie[]) ois.readObject();
                    //System.out.println("Array of objects deserialized successfully:");
                    //for (Movie obj : movies) {
                        //System.out.println("Inside deserialize: " + obj);
                    //}
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("****NOTE: There are no movies present at the moment....");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving receipt");
        }
        if (movies != null && movies.length > 0) {
            for (int i = 0; i < 4; i++) {
                //System.out.println("Movies after deserializing before returning: " + movies[i]);
            }
        }
        else{
            System.out.println("There is no movie existing...Please add movies.");
        }
        return movies;
    }

    public void populateMovies() {
        // System.out.println(Paths.get("").toAbsolutePath().toString());
        try (FileInputStream fis = new FileInputStream(Paths.get("").toAbsolutePath().toString());
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Movie[] deserializedObjects = (Movie[]) ois.readObject();
            System.out.println("Array of objects deserialized successfully:");
            for (Movie obj : deserializedObjects) {
                System.out.println(obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Returns the movie name to the client
    public Movie[] getMovies() {
        return deSerializeMovies();
    }

    // Saves the name from the client
    public void setName(String name) {
        this.name = name;
    }

    // Saves the emailId from the client
    public void setEmailId(String email) {
        emailId = email;
    }

    // Saves the mobileNumber from the client
    public void setMobileNo(long mobile) {
        mobileNo = mobile;
    }

    // Method to display the movie list
    public void displayMovieName() {
        for (int i = 0; i < movies.length; i++) {
            System.out.println(movies[i].getName() + " ");
        }
    }

    // Method to view the movie descriptions
    public void viewMovieDetails() {
        movies = deSerializeMovies();
        System.out.println("Which movie would you like to view?");
        for (Movie  movie: movies) {
            System.out.println("- " + movie);
        }

        String selectedMovie = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < movies.length; i++) {
            if (selectedMovie.equalsIgnoreCase(movies[i].getName())) {
                found = true;
                System.out.println("\nMovie Name: " + movies[i].getName());
                System.out.println("Description: " + movies[i].getDescription());
                System.out.println("Timings: " + movies[i].getTimings());

                System.out.print("\nWould you like to book a ticket for this movie? (Y/N): ");
                char ch = sc.next().charAt(0);
                sc.nextLine(); // consume newline

                if (ch == 'Y' || ch == 'y') {
                    bookTicketProcess(movies[i].getName());
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Movie does not exist!");
        }
    }

    // Method to book the ticket
    public void bookTicket() {
        System.out.println("Which movie would you like to book?");
        for (Movie movie : movies) {
            System.out.println("- " + movie);
        }

        String selectedMovie = sc.nextLine();

        boolean found = false;
        for (Movie movie : movies) {
            if (selectedMovie.equalsIgnoreCase(movie.getName())) {
                found = true;
                bookTicketProcess(movie.getName());
                break;
            }
        }

        if (!found) {
            System.out.println("Movie not found!");
        }
    }

    // Create the seats into a file
    public void createSeatsFile(int seat) {
        try {
            String theatreDetailsFile = "theatre_details.txt";
            // Check if directory exists. Otherwise, create a new directory
            File directory = new File("./theatreInfo");
            if (directory.mkdir()) {
                // System.out.println("Path exists!");
            }

            File file = new File("./theatreInfo", theatreDetailsFile);
            PrintWriter print = new PrintWriter(new FileWriter(file));

            print.println("No of seats booked :" + seat);
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the seats from the file
    public int readSeatsFile() {
        File fileReader = new File("./theatreInfo/theatre_details.txt");
        int availableseats = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileReader))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                String totalSeatNum = line.substring(line.indexOf(':') + 1);
                // System.out.println("File read value: "+totalSeatNum);
                availableseats = Integer.parseInt(totalSeatNum.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return availableseats;
    }

    // Method to start the process
    public void bookTicketProcess(String movie) {
        for (int i = 0; i < movies.length; i++) {
            if (movie.equalsIgnoreCase(movies[i].getTimings())) {
                System.out.println("Timings: " + movies[i].getTimings());
            }
        System.out.print("Choose your preferred timing: ");
        String chosenTime = sc.nextLine();

        int leftSeats = readSeatsFile();
        System.out.println("\nThere are a total of " + leftSeats + " seats available.");
        System.out.println("Each ticket costs Rs." + TICKET_PRICE);

        System.out.print("How many seats would you like to book? ");
        int seats = sc.nextInt();
        sc.nextLine(); // consume newline

        if (seats >= leftSeats) {
            System.out.println("Not enough seats available.");
            return;
        } else {
            leftSeats -= seats;
        }

        double totalAmount = TICKET_PRICE * seats;

        // Create a file with the remaining seats available
        createSeatsFile(leftSeats);

        System.out.print("Would you like refreshments for Rs." + REFRESHMENT_PRICE + "? (Y/N): ");
        char ref = sc.next().charAt(0);
        sc.nextLine(); // consume newline

        boolean refreshments = false;
        if (ref == 'Y' || ref == 'y') {
            totalAmount += REFRESHMENT_PRICE;
            totalAmount -= OFFER_DISCOUNT;
            refreshments = true;
        } else {
            totalAmount -= OFFER_DISCOUNT;

        }

        System.out.println("\nPreparing your ticket...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("\nYour ticket is ready!");
        System.out.println("Movie\t\tSeats\tRefreshments\tDiscount\tTotal Price");
        System.out.println(movie + "\t" + seats + "\t" + ((ref == 'Y' || ref == 'y') ? "Yes" : "No") + "\t\tRs."
                + OFFER_DISCOUNT + "\t\tRs." + totalAmount);
        System.out.println("Do you want to print a receipt for this? Y/N");
        char receiptOption = sc.next().charAt(0);
        if (receiptOption == 'Y' || receiptOption == 'y') {
            // Calls the saveReceipt to save the receipt
            saveReceipt(emailId, mobileNo, name, movie, chosenTime, seats, refreshments, totalAmount);
            System.out.println("Thank you for booking with us!!!");
            System.exit(0);
        } else {
            System.out.println("Thank you for booking with us!!");
            System.exit(0);
        }
    }

    }

    // Method to save the receipt
    public void saveReceipt(String email, long num, String name, String movie, String time, int seats,
            boolean wantsRefreshments, double total) {
        try {
            String fileName = "receipt_" + name + "_" + emailId.replace("[^a-zA-Z0-9]", "_") + ".txt";
            // Check if directory exists. Otherwise, create a new directory
            File directory = new File("./receipts");
            if (directory.mkdir()) {
                System.out.println("Path exists!");
            }
            File file = new File("./receipts", fileName);
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println("-----------------------------MOVIE TICKET-----------------------------");
            out.println("----------------------------------------------------------------------");
            out.println("Name: " + name);
            out.println("Email: " + email);
            out.println("Movie chose: " + movie);
            out.println("Mobile Number: " + num);
            out.println("Timing chosen: " + time);
            out.println("Seats booked: " + seats);
            out.println("Refreshments: " + (wantsRefreshments ? "Yes" : "No"));
            out.println("Discount: " + OFFER_DISCOUNT);
            out.println("Total bill: " + total);
            out.println("------------------------------CUT HERE---------------------------------");
            out.println("Thank you for booking with us!");
            out.close();
            System.out.println("Receipt has been saved as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving receipt");
        }
    }

    // Admin Commands
    /**
     * These methods are only accessible by the admin. These methods are used to reset the number of seats, add new movies, etc. 
     * This method allows the admin to reset the available number of seats.
     * @param seats - Number of seats
     */


    public void resetNoOfAvailableSeats(int seats) {
        try {
            String theatreDetailsFile = "theatre_details.txt";
            // Check if directory exists. Otherwise, create a new directory
            File directory = new File("./theatreInfo");
            if (directory.mkdir()) {
                // System.out.println("Path exists!");
            }

            File file = new File("./theatreInfo", theatreDetailsFile);
            PrintWriter print = new PrintWriter(new FileWriter(file));

            print.println("No of seats booked :" + seats);
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method allows to add new movies to the movie list.
     */
    public void addNewMovie() {
        int noOfMovies = 4;

        // Movie[] movies = new Movie[noOfMovies];
        for (int i = 0; i < noOfMovies; i++) {
            String newMovieName = "";
            System.out.println("Enter the movie name: ");
            newMovieName = sc.next();
            sc.nextLine();

            System.out.println("Enter the movie description: ");
            String movieDescription = sc.nextLine();
            sc.nextLine();

            System.out.println("Enter the timings of the movie: ");
            String movieTimings = sc.nextLine();
            sc.nextLine();

            movies[i] = new Movie(newMovieName, movieDescription, movieTimings);
        }

        serializeMovies(movies);
    }
}

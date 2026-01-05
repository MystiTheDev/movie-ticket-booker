
<img width="1920" height="1080" alt="logo_github2" src="https://github.com/user-attachments/assets/2a343940-b7b7-4ab8-9977-e31d3678b237" />
<h1 align = "center">Movie Ticket Booker Program</h1>

This program allows you to book a ticket for any movie of your choice.
This project is for my school project for the year 2025-26.

# Version
This is **v0.1-RELEASE**.
More updates will be added later.

# Features:
1. A simple UI for easy and convenient access
2. An Admin Panel that can be used for adding new movies or resetting the seat count.
3. Ticket receipts are also saved to a file for easy access
4. The number of seats update everytime any user books a seat.

# How to run this program:
There are two **__ways__** to run this program:
1. By executing the `runClient.bat` file. [**'Method 1'**](#method1)
2. By running it the command way. [**'Method 2'**](#method2)

# #method1
# By executing file [Method 1]:
To run the program directly, just click on the `runClient.bat`. 
> [!NOTE]
> Do note that this file only runs on Windows. For running the program on Linux or any other operating system. Please refer to Method 2

# #method2:
# Running it the command way [Method 2]:
To run the program via command first complie the programs. You must compile **two** programs, namely `ReservationServer.java` and `Client1.java`.
To compile `ReservationServer.java` use the following command:
```java
javac -d ./bin -cp ./bin ./src/reservation/server/ReservationServer.java
``` 
Then you must compile `Client1.java`
```java
javac -d ./bin -cp./bin ./src/reservation/client/Client1.java
```

To run the program simply type:
```java
java -cp ./bin reservation.client.Client1
```

# Under the hood
To check how this program works under the hood, you can go to [src](./src) folder, where the source code is housed.
You can edit or modify this program to suit your needs.
The comments are given at specific places for you to understand what the program does.


# What can you do with this program?
You can use this as a base for any of your projects which are similar to this.

Thank you for viewing my first project🙂

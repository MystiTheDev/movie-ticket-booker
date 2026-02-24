
<img width="1920" height="1080" alt="logo_github2" src="https://github.com/user-attachments/assets/2a343940-b7b7-4ab8-9977-e31d3678b237" />
<h1 align = "center">Movie Ticket Booker Program</h1>

This program allows you to book a ticket for any movie of your choice.
This project is for my school project for the year 2026-27.

# Version
This is **v1.0.1-RELEASE**.
The latest version can be found here: https://github.com/MystiTheDev/movie-ticket-booker/releases

# Features:
1. A simple UI for easy and convenient access
2. An Admin Panel that can be used for adding new movies or resetting the seat count.
3. Ticket receipts are also saved to a file for easy access
4. The number of seats update everytime any user books a seat.

> [!IMPORTANT]
> This is in an very early version. So there are many bugs and missing features. More will be added soon

# How to run this program:
There are two **__ways__** to run this program:
1. By executing the `runClient.bat` file. This is [**Method 1**](#method-1)
2. By running it the command way. This is [**Method 2**](#method-2)

<a name="method-1"></a>
# By executing file [Method 1]:

To run the program directly, just click on the `runClient.bat`. 
If you wish to reset or change the number of seats, or add new movies, run  `runAdminPanel.bat`.

> [!NOTE]
> This method only works on Windows and is generally not recommended. To run this application on Mac or Linux or any other operating system, please refer to [Method 2](#method2)



<a name="method-2"></a>
# Running it the command way [Method 2]:
To run the program via command first complie the programs. You must compile **two** programs, namely `ReservationServer.java` and `Client1.java`.

> [!CAUTION]
> When compiling, make sure you are in the directory [src](./src). If you are not in this directory, it will NOT compile!!

To compile `ReservationServer.java` use the following command:
```java
javac -d ./bin -cp ./bin ./src/reservation/server/ReservationServer.java
``` 
Then you must compile `Client1.java`
```java
javac -d ./bin -cp./bin ./src/reservation/client/Client1.java
```

To run the program use the:
```java
java -cp ./bin reservation.client.Client1
```

# Under the hood
To check how this program works under the hood, you can go to [src](./src) folder, where the source code is housed.
You can edit or modify this program to suit your needs.
The comments are given at specific places for you to understand what the program does.
You can modify and use this program for any needs or you can take parts of this program and use it somewhere else as well.s

# How does it work?
Well, there are two parts of this code:
1. The client side(What the user sees)
2. The server side(What happens in the background)

Now, the user enters the necessary details in the client panel. This data then gets transfered to the server side and the necessary functions are called.


# What can you do with this program?
You can use this as a base for any of your projects which are similar to this.

Thank you for viewing my first project🙂

Made by:
**K.Ishan**
**Class XII 'A' Section**
**2026-27**

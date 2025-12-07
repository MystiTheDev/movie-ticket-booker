# Movie Ticket Booker Program
This program allows you to book a ticket for any movie of your choice. 

# Features:
1. A simple UI for easy and convenient access
2. An Admin Panel for the Theatre Admin
3. Ticket receipts are also saved to a file for easy access
4. The number of seats update everytime any user books a seat.

# How to run this program:
There are two **__ways__** to run this program:
1. By executing the `runClient.bat` file. [**'Method 1'**](#method1)
2. By running it the command way. [Method 2](#method2)

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
# What can you do with this program?
You can use this as a base for any of your projects which are similar to this.

Thank you for viewing my first project🙂

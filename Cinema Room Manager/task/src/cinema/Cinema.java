package cinema;
import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);
    static int seats;
    static int bookedSeats;
    static int currentIncome;

    static String[][] initialize(int x, int y) {
        String[][] cinema = new String[x][y];

        for(int j=0; j<x; j++) {
            for(int i=0; i<y; i++){
                cinema[j][i] = "S ";
            }
        }

        return cinema;
    }

    static void display(String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int j = 0; j <= cinema.length; j++) {
            if(j > 0) {
                System.out.print(j + " ");
            }

            for (int i = 0; i < cinema[0].length; i++) {
                if (j == 0) {
                    System.out.print(i+1 + " ");
                } else {
                    System.out.print(cinema[j-1][i]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bookSeat(int x, int y, String[][] cinema) {

        try {
            if ("B ".equals(cinema[x-1][y-1])) {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                bookTicket(cinema);
                return;
            } else {
                cinema[x-1][y-1] = "B ";
                ++bookedSeats;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            System.out.println();
            bookTicket(cinema);
            return;
        }



        int rows = cinema.length;
        int cols = cinema[0].length;
        int seats = rows * cols;
        if (seats <= 60) {
            System.out.println("Ticket price: $10");
            currentIncome += 10;
        } else if (x <= (rows/2)){
            System.out.println("Ticket price: $10");
            currentIncome += 10;
        } else {
            System.out.println("Ticket price: $8");
            currentIncome += 8;
        }
        System.out.println();
    }

    static void bookTicket(String[][] cinema) {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int col = scanner.nextInt();

        bookSeat(row, col, cinema);
    }

    static void statistics(String[][] cinema) {
        double percentage = Math.round((bookedSeats/(double)seats) * 10000.0) /100;
        int rows = cinema.length;
        int cols = cinema[0].length;
        int totalIncome = seats <= 60 ? (seats * 10) : (rows/2) * cols * 10 + (rows-rows/2) * cols * 8;

        System.out.println("Number of purchased tickets: " + bookedSeats);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        // Write your code here

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();
        String[][] cinema = initialize(rows, cols) ;
        seats = rows * cols;

        menu: while (true) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int condition = scanner.nextInt();

            switch (condition) {
                case 1:
                    display(cinema);
                    break;

                case 2:
                    bookTicket(cinema);
                    break;

                case 3:
                    statistics(cinema);
                    break;

                case 0:
                    break menu;

                default:

            }

        }

    }
}
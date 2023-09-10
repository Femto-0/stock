import java.util.Scanner;

public class Broker {
    /*
    main method
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine(); //take input from the user for Symbol of the Stock

        System.out.print("Enter initial volume: ");
        int initialVolume = scanner.nextInt(); //Input from the user for initial Volume of the stock

        System.out.print("Enter initial price: $");
        double initialPrice = scanner.nextDouble(); //Input from the user for initial price of the stock

        Stock stock = new Stock(symbol, initialVolume, initialPrice);

        System.out.println("\nCurrent State:");
        stock.currentState();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. End");
            System.out.print("Select an option (1/2/3): ");

            int option = scanner.nextInt();

/*
if else statement to keep the user involved in buying and selling of the stock until they choose to end voluntarily
 */
            if (option == 1) { //"1" --> user wants to buy
                System.out.print("Enter the number of stocks to buy: ");
                int number = scanner.nextInt();
                System.out.print("Enter the new price: $");
                double newPrice = scanner.nextDouble();
                stock.buy(number, newPrice);
            } else if (option == 2) { //--> user wants to sell
                System.out.print("Enter the number of stocks to sell: ");
                int number = scanner.nextInt();
                System.out.print("Enter the new price: $");
                double newPrice = scanner.nextDouble();
                stock.sell(number, newPrice);
            } else if (option == 3) { // --> user wants to stop trading
                stock.currentState(); //display the current state of the stock after the user chooses to end.
                break;
            } else {
                System.out.println("Invalid option. Please select 1, 2, or 3."); //in case the user inputs anything other than 1,2 or 3
            }

            System.out.println("\nUpdated State:"); //prints the updated Stocks info after each Purchase or sales of stocks
            stock.currentState();
        }

        scanner.close();
    }
}

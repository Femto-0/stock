import java.util.Scanner;

public class Broker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stock stock = null;
        Customer customer = null;

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Create Stock");
            System.out.println("2. Create Customer");
            System.out.println("3. Display Status");
            System.out.println("4. Buy (Customer)");
            System.out.println("5. Sell (Customer)");
            System.out.println("6. End");
            System.out.print("Select an option (1/2/3/4/5/6): ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter stock symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter initial volume: ");
                    double initialVolume = scanner.nextDouble();
                    System.out.print("Enter initial price: $");
                    double initialPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    stock = new Stock(symbol, initialVolume, initialPrice);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter initial balance: $");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    customer = new Customer(customerName, balance);
                    break;

                case 3:
                    if (stock != null) {
                        stock.currentState();
                    }
                    if (customer != null) {
                        customer.currentState();
                    }
                    break;

                case 4:
                    if (customer != null && stock != null) {
                        System.out.print("Enter number of stocks to buy: ");
                        double buyNumber = scanner.nextDouble();
                        System.out.print("Enter new price: $");
                        double buyPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        customer.buy(stock, buyNumber, buyPrice);
                        stock.currentState();
                        customer.currentState();
                    } else {
                        System.out.println("Stock or customer not initialized.");
                    }
                    break;

                case 5:
                    if (customer != null && stock != null) {
                        System.out.print("Enter number of stocks to sell: ");
                        double sellNumber = scanner.nextDouble();
                        System.out.print("Enter new price: $");
                        double sellPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        customer.sell(stock, sellNumber, sellPrice);
                        stock.currentState();
                        customer.currentState();
                    } else {
                        System.out.println("Stock or customer not initialized.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }
    }
}
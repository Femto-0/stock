import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Broker {
    private static String item_file= "src/item.dat";
    private static String customer_file="src/customer.dat";

    private static List<Stock> stocks= new ArrayList<>();
    private static List<Customer> customers= new ArrayList<>();

/*
Method to read info of the stock and Customer form "item.dat" and "customer.dat" file.
 */
    private static void readInfo() throws IOException {
        stocks.clear();
        BufferedReader stockReader = new BufferedReader(new FileReader(item_file));
        BufferedReader customerReader= new BufferedReader(new FileReader(customer_file));
        String line;
        /*
        Load Stocks
         */
        while((line=stockReader.readLine())!=null){
            String[] parts = line.split(", ",3);
            if(parts.length==3){
                String name= parts[0];
                Double price = Double.parseDouble(parts[1]);
                Double volume= Double.parseDouble(parts[2]);
                stocks.add(new Stock(name,volume,price)); //adding the whole thing to "stocks" Array list
            }

        }
        /*
        Load Customer info
         */
        while((line=customerReader.readLine())!=null){
            String[] parts= line.split(", ");
            if(parts.length>=3){
                String customerName= parts[0];
                Double customerBalance= Double.parseDouble(parts[1]);
                Customer customer= new Customer(customerName,customerBalance);
                for (int i = 2; i < parts.length; i += 3) {
                    String stockName = parts[i];
                    double stockVolume = Double.parseDouble(parts[i + 2]);
                    double stockPrice = Double.parseDouble(parts[i + 1]);
                    Stock stock = new Stock(stockName, stockVolume, stockPrice);
                    customer.addStock(stock, stockVolume);
                }
                customers.add(customer);
            }
        }
    }
    /*
    method to write/update stock and customer info in "item.dat" and "customer.dat" file
     */
    private static void saveStocksAndCustomers() throws IOException {
        try (BufferedWriter stockWriter = new BufferedWriter(new FileWriter(item_file));
             BufferedWriter customerWriter = new BufferedWriter(new FileWriter(customer_file))) {

            // Save stocks
            for (Stock stock : stocks) {
                stockWriter.write(stock.getSymbol() + ", " + stock.getPrice() + ", " + stock.getVolume());
                stockWriter.newLine();
            }

            // Save customers
            for (Customer customer : customers) {
                customerWriter.write(customer.getName() + ", " + customer.getCurrentBalance());
                Map<String, Stock> customerStocks = customer.getStocks();
                for (Stock stock : customerStocks.values()) {
                    customerWriter.write(", " + stock.getSymbol() + ", " + stock.getPrice() + ", " + stock.getVolume());
                }
                customerWriter.newLine();
            }
        }
    }
    private static void displayStocks() {
        System.out.println("\nStocks:");
        for (Stock stock : stocks) {
            System.out.println(stock.toString());
        }
    }

    private static void displayCustomers() {
        System.out.println("\nCustomers:");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    private static void buyStock(Customer customer, Stock stock, double number, double newPrice) throws IOException {
        customer.buy(stock, number, newPrice);
        stock.buy(number, newPrice);
        System.out.println("Transaction successful: Bought " + number + " stocks for $" + (number * newPrice));
    }

    private static void sellStock(Customer customer, Stock stock, double number, double newPrice) throws IOException {
        customer.sell(stock, number, newPrice);
        stock.sell(number, newPrice);
        System.out.println("Transaction successful: Sold " + number + " stocks for $" + (number * newPrice));
        saveStocksAndCustomers();
    }

    private static Customer findCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    private static Stock findStock(String stockSymbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(stockSymbol)) {
                return stock;
            }
        }
        return null; // Stock not found
    }

    public static void main(String[] args) throws IOException {
        readInfo();   //populating the "stocks" list
        Scanner scanner = new Scanner(System.in);
        Stock stock = null;
        Customer customer = null;


        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. display Stocks");
            System.out.println("2. display Customer");
            System.out.println("3. Buy (Customer");
            System.out.println("4. Sell (Customer)");
            System.out.println("5. End");

            System.out.print("Select an option (1/2/3/4/5/6): ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
               displayStocks();   //display all the stocks
                    break;

                case 2:
                    displayCustomers(); //display all the customers
                    break;


                case 3: //buy(customer)
                    System.out.print("Enter customer's name: ");
                    String customerNameBuy = scanner.nextLine();
                    Customer customerToBuy = findCustomer(customerNameBuy);
                    if (customerToBuy != null) {
                        displayStocks();
                        System.out.print("Enter the stock symbol to buy: ");
                        String stockSymbolBuy = scanner.nextLine();
                        Stock stockToBuy = findStock(stockSymbolBuy);
                        if (stockToBuy != null) {
                            System.out.print("Enter number of stocks to buy: ");
                            double buyNumber = scanner.nextDouble();
                            System.out.print("Enter new price: $");
                            double buyPrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            buyStock(customerToBuy, stockToBuy, buyNumber, buyPrice);
                            saveStocksAndCustomers(); //updating the new info
                        } else {
                            System.out.println("Stock not found.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    // Sell (Customer)
                    System.out.print("Enter customer's name: ");
                    String customerNameSell = scanner.nextLine();
                    Customer customerToSell = findCustomer(customerNameSell);
                    if (customerToSell != null) {
                        displayStocks();
                        System.out.print("Enter the stock symbol to sell: ");
                        String stockSymbolSell = scanner.nextLine();
                        Stock stockToSell = findStock(stockSymbolSell);
                        if (stockToSell != null) {
                            System.out.print("Enter number of stocks to sell: ");
                            double sellNumber = scanner.nextDouble();
                            System.out.print("Enter new price: $");
                            double sellPrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            sellStock(customerToSell, stockToSell, sellNumber, sellPrice);
                            saveStocksAndCustomers(); //updating the info
                        } else {
                            System.out.println("Stock not found.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 5:
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
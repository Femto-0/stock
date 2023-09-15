import java.util.Scanner;

public class Stock {
    private String symbol;
    private double volume;
    private double price;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


/*
Stock constructor with Symbol, initial volume and initial price of the stock.
 */
    public Stock(String symbol, double initialVolume, double initialPrice) {
        this.symbol = symbol;
        this.volume = initialVolume;
        this.price = initialPrice;
    }
/*
method for when the user choses to buy.
 */
    public void buy(double number, double newPrice) {
        if (number > 0 && number <= volume) {
            volume -= number;
            price = newPrice;
        } else {
            System.out.println("Invalid buy operation. You can't buy more than available volume.");
        }
    }
    /*
    method for when the user choses to sell.
     */
    public void sell(double number, double newPrice) {
        if (number > 0) {
            volume += number;
            price = newPrice;
        } else {
            System.out.println("Invalid sell operation. Number of stocks to sell must be greater than 0.");
        }
    }
/*
method to print the current Volume, Price and Value of the share.
 */
    public void currentState() {
        /*
        in case we need to update Customer's info thorugh this method
         */
//        String name="";
//        double balance=0;
//        CustomerClass customerClass= new CustomerClass(name, balance); //initializing the customer class to update users info after every successful purchase/ sales
//        name=customerClass.getCustomerName(); //Name of customer
//        balance=customerClass.getCurrentBalance();  //current balance of customer
//        double currentStocks= customerClass.getStocksInHand(); //current stocks of customer
//
//        System.out.println("Name of Customer: "+ name);
//        System.out.println("Current Balance of Customer: "+balance);
//        System.out.println("Current Stocs in hold of Customer: "+ currentStocks);
        double value = volume * price;
        System.out.println("---------------------");
        System.out.println("|Symbol: " + symbol);
        System.out.println("|Volume: " + volume);
        System.out.println("|Price: $" + price);
        System.out.println("|Value: $" + value);
    }
    public void createNewStock(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter stock symbol: ");
         symbol = scanner.nextLine(); //take input from the user for Symbol of the Stock

        System.out.print("Enter initial volume: ");
         volume = scanner.nextInt(); //Input from the user for initial Volume of the stock

        System.out.print("Enter initial price: $");
        price = scanner.nextDouble(); //Input from the user for initial price of the stock
    }

}



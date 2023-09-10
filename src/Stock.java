public class Stock {
    private String symbol;
    private int volume;
    private double price;
/*
Stock constructor with Symbol, initial volume and initial price of the stock.
 */
    public Stock(String symbol, int initialVolume, double initialPrice) {
        this.symbol = symbol;
        this.volume = initialVolume;
        this.price = initialPrice;
    }
/*
method for when the user choses to buy.
 */
    public void buy(int number, double newPrice) {
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
    public void sell(int number, double newPrice) {
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
        double value = volume * price;
        System.out.println("Symbol: " + symbol);
        System.out.println("Volume: " + volume);
        System.out.println("Price: $" + price);
        System.out.println("Value: $" + value);
    }
}



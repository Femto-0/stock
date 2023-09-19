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
    public String toString() {
        return "Stock Name: " + symbol + ", Price: " + price + ", Volume: " + volume;
    }

}



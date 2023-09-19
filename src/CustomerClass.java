import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

class Customer {
    private String name;
    private double currentBalance;
    private double stocksInHand;


    /*
    getter and setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getStocksInHand() {
        return stocksInHand;
    }

    public void setStocksInHand(double stocksInHand) {
        this.stocksInHand = stocksInHand;
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String, Stock> stocks) {
        this.stocks = stocks;
    }

    private Map<String, Stock> stocks;

    public Customer(String name, double currentBalance) {
        this.name = name;
        this.currentBalance = currentBalance;
        this.stocks = new HashMap<>();

    }
    /*
    creating a isValidTransaction so that we can validate buying and selling
     */

    private boolean isValidTransaction(Stock stock, double number, double newPrice) {
        if (number <= 0) {
            System.out.println("Invalid transaction: Number must be positive.");
            return false;
        }

        if (stock == null) {
            System.out.println("Invalid transaction: Stock not initialized.");
            return false;
        }

        double currentPrice = stock.getPrice();
        double priceDifference = Math.abs(newPrice - currentPrice);
        double priceDifferencePercentage = (priceDifference / currentPrice) * 100.0;

        if (priceDifferencePercentage > 5.0) {
            System.out.println("Invalid transaction: Price difference exceeds 5%.");
            return false;
        }
        return true;
    }

    public void buy(Stock stock, double number, double newPrice) {
            if (currentBalance < (number * newPrice)) {
                System.out.println("Cannot buy stocks, insufficient balance");
                System.out.println("-------------------------------------------");
                stock.buy(number,newPrice);
            } else if (isValidTransaction(stock, number, newPrice)) {
                stocksInHand += number;
                currentBalance -= (number * newPrice);
                stock.buy(number, newPrice);
            }
        }

    public void sell(Stock stock, double number, double newPrice) {
        if (isValidTransaction(stock, number, newPrice)) {
            if (number > stocksInHand) { //in case we don't have enough stocks to sel
                System.out.println("Invalid transaction: Not enough stocks in hand to sell.");
                System.out.println("-------------------------------------------");
                stock.sell(number,newPrice);
            } else {
                stocksInHand -= number;
                currentBalance += (number * newPrice);
                stock.sell(number, newPrice);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", Balance: ").append(currentBalance).append("\n");
        sb.append("Stocks:\n");
        for (Stock stock : stocks.values()) {
            sb.append(stock).append("\n");
        }
        return sb.toString();
    }

    public void addStock(Stock stock, double stockVolume) {
        if (stocks.containsKey(stock.getSymbol())) {
            Stock existingStock = stocks.get(stock.getSymbol());
            existingStock.setVolume(existingStock.getVolume() + stockVolume);
        } else {
            stocks.put(stock.getSymbol(), new Stock(stock.getSymbol(), stock.getPrice(), stockVolume));
        }
    }
}

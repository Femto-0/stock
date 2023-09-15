import java.text.DecimalFormat;

class Customer {
    private String name;
    private double currentBalance;
    private double stocksInHand;

    public Customer(String name, double currentBalance) {
        this.name = name;
        this.currentBalance = currentBalance;
        this.stocksInHand = 0;
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

    public void currentState() {
        DecimalFormat df = new DecimalFormat("$ #0.00");
        System.out.println("|Customer: " + name);
        System.out.println("|Balance: " + df.format(currentBalance));
        System.out.println("|Stocks In Hand: " + stocksInHand);
        System.out.println("----------------------------------------");
    }

}

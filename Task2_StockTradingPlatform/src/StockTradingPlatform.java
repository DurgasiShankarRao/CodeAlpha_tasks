import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {
    static Map<String, Double> marketData = new HashMap<>();
    static Map<String, Integer> portfolio = new HashMap<>();
    static double balance = 10000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//Market Data
        marketData.put("AAPL", 150.0);     // Apple
        marketData.put("GOOG", 2700.0);    // Google
        marketData.put("AMZN", 3300.0);    // Amazon
        marketData.put("INFY", 1400.0);    // Infosys
        marketData.put("TCS", 3600.0);     // Tata Consultancy Services
        marketData.put("MSFT", 280.0);     // Microsoft
        marketData.put("NFLX", 500.0);     // Netflix
        marketData.put("WIPRO", 400.0);    // Wipro



        System.out.println("📈 Welcome to CodeAlpha Stock Trading Platform!");
        int choice;

        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewMarketData();
                case 2 -> buyStock(scanner);
                case 3 -> sellStock(scanner);
                case 4 -> viewPortfolio();
                case 5 -> System.out.println("💼 Thank you for using CodeAlpha Trading Platform!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    static void viewMarketData() {
        System.out.println("\n📊 Market Data:");
        for (Map.Entry<String, Double> entry : marketData.entrySet()) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }

    static void buyStock(Scanner scanner) {
        System.out.print("\nEnter stock symbol to buy: ");
        String stock = scanner.next().toUpperCase();
        if (!marketData.containsKey(stock)) {
            System.out.println("❌ Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        double price = marketData.get(stock);
        double cost = price * qty;

        if (cost > balance) {
            System.out.println("❌ Insufficient balance!");
        } else {
            balance -= cost;
            portfolio.put(stock, portfolio.getOrDefault(stock, 0) + qty);
            System.out.println("✅ Purchased " + qty + " shares of " + stock);
        }
    }

    static void sellStock(Scanner scanner) {
        System.out.print("\nEnter stock symbol to sell: ");
        String stock = scanner.next().toUpperCase();
        if (!portfolio.containsKey(stock)) {
            System.out.println("❌ You don’t own this stock.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        int ownedQty = portfolio.get(stock);
        if (qty > ownedQty) {
            System.out.println("❌ You don't have that many shares.");
        } else {
            double price = marketData.get(stock);
            double gain = price * qty;
            balance += gain;
            portfolio.put(stock, ownedQty - qty);
            System.out.println("✅ Sold " + qty + " shares of " + stock);
        }
    }

    static void viewPortfolio() {
        System.out.println("\n💼 Your Portfolio:");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned yet.");
        } else {
            for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
                String stock = entry.getKey();
                int qty = entry.getValue();
                double value = marketData.get(stock) * qty;
                System.out.println(stock + ": " + qty + " shares ($" + value + ")");
            }
        }
        System.out.println("💰 Current Balance: $" + balance);
    }
}

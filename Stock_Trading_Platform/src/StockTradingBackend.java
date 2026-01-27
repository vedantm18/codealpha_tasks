import java.sql.*;
import java.util.Scanner;

public class StockTradingBackend {

    static Scanner sc = new Scanner(System.in);

    // View all stocks
    public static void viewStocks() {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM stocks";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nID\tNAME\tPRICE");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View portfolio
    public static void viewPortfolio(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            String sql = """
                SELECT s.name, p.quantity, s.price, p.available_balance
                FROM portfolio p
                JOIN stocks s ON p.stock_id = s.id
                WHERE p.user_id = ?
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nSTOCK\tQTY\tPRICE\tBALANCE");
            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + "\t" +
                        rs.getInt("quantity") + "\t" +
                        rs.getDouble("price") + "\t" +
                        rs.getDouble("available_balance")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buy stock
    public static void buyStock(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Stock ID: ");
            int stockId = sc.nextInt();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            PreparedStatement ps1 =
                    con.prepareStatement("SELECT price FROM stocks WHERE id = ?");
            ps1.setInt(1, stockId);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                System.out.println("Invalid Stock ID");
                return;
            }

            double price = rs1.getDouble("price");
            double totalCost = price * qty;

            PreparedStatement ps2 =
                    con.prepareStatement(
                        "SELECT available_balance FROM portfolio WHERE user_id=? AND stock_id=?"
                    );
            ps2.setInt(1, userId);
            ps2.setInt(2, stockId);
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.next()) {
                System.out.println("Stock not found in portfolio");
                return;
            }

            double balance = rs2.getDouble("available_balance");

            if (balance < totalCost) {
                System.out.println("Insufficient balance");
                return;
            }

            PreparedStatement ps3 =
                    con.prepareStatement("""
                        UPDATE portfolio
                        SET quantity = quantity + ?,
                            available_balance = available_balance - ?
                        WHERE user_id = ? AND stock_id = ?
                    """);

            ps3.setInt(1, qty);
            ps3.setDouble(2, totalCost);
            ps3.setInt(3, userId);
            ps3.setInt(4, stockId);
            ps3.executeUpdate();

            System.out.println("Stock bought successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sell stock
    public static void sellStock(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Stock ID: ");
            int stockId = sc.nextInt();

            System.out.print("Enter Quantity to Sell: ");
            int qty = sc.nextInt();

            PreparedStatement ps1 =
                    con.prepareStatement(
                        "SELECT quantity, available_balance FROM portfolio WHERE user_id=? AND stock_id=?"
                    );
            ps1.setInt(1, userId);
            ps1.setInt(2, stockId);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                System.out.println("Stock not found in portfolio");
                return;
            }

            int ownedQty = rs1.getInt("quantity");

            if (qty > ownedQty) {
                System.out.println("Not enough stock to sell");
                return;
            }

            PreparedStatement ps2 =
                    con.prepareStatement("SELECT price FROM stocks WHERE id=?");
            ps2.setInt(1, stockId);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();

            double credit = rs2.getDouble("price") * qty;

            PreparedStatement ps3 =
                    con.prepareStatement("""
                        UPDATE portfolio
                        SET quantity = quantity - ?,
                            available_balance = available_balance + ?
                        WHERE user_id = ? AND stock_id = ?
                    """);

            ps3.setInt(1, qty);
            ps3.setDouble(2, credit);
            ps3.setInt(3, userId);
            ps3.setInt(4, stockId);
            ps3.executeUpdate();

            System.out.println("Stock sold successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        int choice;
        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Stocks");
            System.out.println("2. View Portfolio");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewStocks();
                case 2 -> viewPortfolio(userId);
                case 3 -> buyStock(userId);
                case 4 -> sellStock(userId);
                case 0 -> System.out.println("Thank you for trading!");
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }
}

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   private final static String URL="jdbc:mysql://localhost:3306/stock_trading_db";
   

   private static final String USER="root";
   private static final String PASSWORD="vedantsql";

   public static Connection getConnection() throws Exception{
    return DriverManager.getConnection(URL, USER, PASSWORD);
   }
   
   
}

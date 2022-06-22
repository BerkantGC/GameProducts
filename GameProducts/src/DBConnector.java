import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public String DB_USER = "postgres";
    public String DB_URL = "jdbc:postgresql://localhost:5432/gameproducts";
    public String DB_PASS = "root";

    Connection connect = null;

    public Connection connectDB() {
        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.connect;
    }

    public static Connection getInstance()
    {
        DBConnector db = new DBConnector();
        return db.connectDB();
    }

    public static void main(String[] args)
    {
        Connection conn = DBConnector.getInstance();
        getAverage.getAveragePrice(conn);
    }
}

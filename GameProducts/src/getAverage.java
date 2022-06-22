import java.sql.*;
import java.util.Scanner;

public class getAverage {
    public static void getAveragePrice(Connection conn)
    {
        Statement st = null;
        try {
            st = DBConnector.getInstance().createStatement();
            ResultSet rs = null;
            String query = "SELECT " +
                    "    pro.product_name AS game_name, " +
                    "    com.company_name, " +
                    "    CASE WHEN pur.type = 1 " +
                    "       THEN 'ALIM' " +
                    "       ELSE 'SATIM' " +
                    "    END as Saleable," +
                    "    COUNT(pur.*) AS sold_amount," +
                    "    CAST(AVG(pur.price) as DECIMAL(10,2)) as average_sold_price " +
                    "FROM purchases AS pur " +
                    "JOIN companies AS com ON com.id = pur.company_id " +
                    "JOIN products AS pro ON pro.id = pur.product_id " +
                    "JOIN prices AS pr ON pr.product_id = pro.id " +
                    "GROUP BY pro.product_name, com.company_name, pur.type " +
                    "ORDER BY pro.product_name;";

            /*
            rs.absolute(10);
            System.out.println(rs.getString("game_name"));*/


            /*Scanner scan = new Scanner(System.in);
            String new_game_name;

            new_game_name = scan.next();*/

            /*String insertPurchases = "INSERT INTO purchases(product_id, price, company_id, type) " +
                                    " VALUES(?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(insertPurchases);
            pstmt.setInt(1, 2);
            pstmt.setFloat(2, (float) 165.45);
            pstmt.setInt(3, 1);
            pstmt.setInt(4, 1);

            pstmt.execute();*/

            rs =  st.executeQuery(query);
            while (rs.next())
            {
                System.out.println("Game's Name: " + rs.getString("game_name") +
                                                " || Company: " + rs.getString("company_name") + "|| Satis Turu: " + rs.getString("saleable") +
                                                " || Satis Sayisi: " + rs.getInt("sold_amount") + " || Ortalama Fiyat: " + rs.getFloat("average_sold_price"));
                System.out.println("\n\n##########################################################################\n\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

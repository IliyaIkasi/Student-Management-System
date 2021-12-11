package project;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    Connection conn = null;
    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/art_college", "root", "Password");

            return connection;

        } catch (Exception e) {
            return null;
        }
    }
}

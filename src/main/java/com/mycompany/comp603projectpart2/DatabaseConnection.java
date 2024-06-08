
package com.mycompany.comp603projectpart2;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:derby:gameDatabase;create=true";

    static {
        try {
            // Load the Derby JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        System.out.println("Database connection established.");
        return conn;
    }

    public static void setupDatabase() {
        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Setting up the database...");

            // Check if USERS table exists
            if (!tableExists(conn, "USERS")) {
                String createUserTable = "CREATE TABLE Users ("
                        + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                        + "username VARCHAR(100) UNIQUE NOT NULL, "
                        + "password VARCHAR(100) NOT NULL, "
                        + "email VARCHAR(100))";
                stmt.executeUpdate(createUserTable);
                System.out.println("Users table created.");
            }

            // Check if SCORES table exists
            if (!tableExists(conn, "SCORES")) {
                String createScoresTable = "CREATE TABLE Scores ("
                        + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                        + "user_id INT, "
                        + "score INT, "
                        + "FOREIGN KEY (user_id) REFERENCES Users(id))";
                stmt.executeUpdate(createScoresTable);
                System.out.println("Scores table created.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }
}

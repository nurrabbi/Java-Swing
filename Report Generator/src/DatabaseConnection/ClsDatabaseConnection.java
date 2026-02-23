package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClsDatabaseConnection {
    /* A connection (session) with a specific database. SQL statements are
    executed and results are returned within the context of a connection. */
    static Connection DBCon;

    public static Connection methodDatabaseConnection() throws SQLException {
        try {
            //Invoking JDBC driver. Returns the Class object associated
            //with the class or interface with the given string name.
            Class.forName("com.mysql.jdbc.Driver");
            //Establish database (sms) connection with JDBC | "sms" is database name.
            DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "");
            System.out.println("Connection establish successfully...");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection problem!!! \n" + ex.toString());
        }
        return DBCon;
    }
}
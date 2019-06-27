package pl.coderslab.warsztat2.zadanie1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztat2?" +
                        "useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
                "root", "coderslab");
    }

}


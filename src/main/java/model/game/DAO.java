package model.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private static final String server = "localhost";
    private static final String database = "woowa";
    private static final String userName = "donut";
    private static final String password = "qwer1234";

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
                    userName,
                    password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
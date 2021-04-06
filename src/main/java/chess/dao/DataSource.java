package chess.dao;

import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static final String server ="localhost:13306";
    private static final String database = "chess";
    private static final String option = "?useSSL=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "root";

    public Connection getConnection() {
        setJdbc();
        return connectToDB();
    }

    private Connection connectToDB() {
        Connection con;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }

        return con;
    }

    private void setJdbc() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DataAccessException(e);
        }
    }

}

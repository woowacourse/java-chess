package chess.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection getConnection() throws JDBCDriverLoadException, JDBCConnectException {
        Connection con = null;
        String server = "localhost";
        String database = "chess";
        String userName = "root";
        String password = "soorealbutnice";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new JDBCDriverLoadException(" !! JDBC Dirver load 오류 : " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
        } catch (SQLException e) {
            throw new JDBCConnectException("연결 오류 : " + e.getMessage());
        }

        return con;
    }
}

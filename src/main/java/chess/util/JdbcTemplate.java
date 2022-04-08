package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {

    private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection(final String url) {
        Connection conn = null;
        try {
            Class.forName(SQL_DRIVER);
            conn = DriverManager.getConnection(url, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new SqlConnectionException(SqlConnectionException.MESSAGE, e);
        }
        return conn;
    }

}

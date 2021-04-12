package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chessdb";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        Connection con = null;

        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new SQLException("데이터베이스 드라이버를 찾는데 실패했습니다.", e);
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("데이터베이스에 접근하는데 실패했습니다.", e);
        }

        return con;
    }
}

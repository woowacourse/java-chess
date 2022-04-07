package chess.utils;

import java.sql.*;

public class DbConnector {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            System.out.println("커넥션 연결에 실패하였습니다.");
            e.printStackTrace();
        }
        return conn;
    }

    public static int getCountResult(final String sql) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 계산을 실패하였습니다.");
        }
    }
}

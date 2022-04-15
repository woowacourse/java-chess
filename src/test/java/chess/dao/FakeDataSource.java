package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FakeDataSource implements DataSource{

    private final String url = "jdbc:mysql://localhost:3306/chess";
    private final String user = "user";
    private final String password = "password";

    @Override
    public Connection connection() {
        loadDriver();
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] DB 연결 실패");
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("[ERROR] 드라이버 로드 실패");
        }
    }
}

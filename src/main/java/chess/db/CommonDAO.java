package chess.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommonDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";

    protected String roomId;

    protected CommonDAO(String roomId) {
        this.roomId = roomId;
    }

    protected CommonDAO() {
    }

    protected Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(URL, "user", "password");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void setStrings(PreparedStatement statement, List<String> strings) throws SQLException {
        for (int i = 0; i < strings.size(); i++) {
            statement.setString(i + 1, strings.get(i));
        }
    }
}

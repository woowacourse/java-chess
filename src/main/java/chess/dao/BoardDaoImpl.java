package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class BoardDaoImpl implements BoardDao{

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Override
    public void save(int gameId, Map<String, String> StringPieceMapByPiecesByPositions) {

    }

    @Override
    public Map<String, String> findById(int gameId) {
        return null;
    }

    @Override
    public void deleteById(int gameId) {

    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

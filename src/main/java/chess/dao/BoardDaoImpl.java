package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDaoImpl implements BoardDao{

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Override
    public void save(int gameId, Map<String, String> boardMap) {
        Connection connection = getConnection();
        String sql = "insert into board (game_id, position, piece) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Map.Entry<String, String> entry : boardMap.entrySet()) {
                statement.setInt(1, gameId);
                statement.setString(2, entry.getKey());
                statement.setString(3, entry.getValue());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Map<String, String> findById(int gameId) {
        Connection connection = getConnection();
        String sql = "select position, piece from board where game_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            Map<String, String> boardMap = new HashMap<>();

            while (resultSet.next()) {
                boardMap.put(resultSet.getString("position"),
                    resultSet.getString("piece"));
            }
            return boardMap;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int gameId) {
        Connection connection = getConnection();
        String sql = "delete from board where game_id = " + "'" + gameId + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

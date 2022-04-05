package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SquareDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(Map<String, String> squares) {
        for (String position : squares.keySet()) {
            saveOf(position, squares.get(position));
        }
    }

    private void saveOf(String position, String piece) {
        final Connection connection = getConnection();
        final String sql = "insert into square (position, piece) values (?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> find() {
        final Map<String, String> squares = new HashMap();
        final Connection connection = getConnection();
        final String sql = "select position, piece from square where id <= 64";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (!resultSet.next()) {
                squares.put(resultSet.getString("position"), resultSet.getString("piece"));
            }
            return squares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squares;
    }
}

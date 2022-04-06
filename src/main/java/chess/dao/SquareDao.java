package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
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

    public void save(Map<Position, Piece> squares) {
        for (Position position : squares.keySet()) {
            saveOf(position, squares.get(position));
        }
    }

    private void saveOf(Position position, Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into square (position, team, symbol) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.toString());
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getSymbol());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> find() {
        final Map<String, String> squares = new HashMap();
        final Connection connection = getConnection();
        final String sql = "select position, team, symbol from square";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                squares.put(resultSet.getString("position"),
                        resultSet.getString("team") + "_" + resultSet.getString("symbol"));
            }
            return squares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squares;
    }

    public void delete() {
        final Connection connection = getConnection();
        final String sql = "delete from square";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

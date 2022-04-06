package chess.dao;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.PieceMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String position, String piece, String color, int gameId) {
        Connection connection = getConnection();
        final String sql = "insert into board (position, piece, color, game_id) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.setInt(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String position, String piece, String color, int id) {
        Connection connection = getConnection();
        final String sql = "insert into board (position, piece, color, game_id) values (?, ?, ?, ?)"
                + "on duplicate key update piece = ?, color = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.setInt(4, id);
            statement.setString(5, piece);
            statement.setString(6, color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Position, Piece> findGame(int id) {
        Connection connection = getConnection();
        final String sql = "select position, piece, color from board where game_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Map<Position, Piece> board = new HashMap<>();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("position"));
                Color color = Color.of(resultSet.getString("color"));
                Piece piece = PieceMapper.of(resultSet.getString("piece"), color);
                board.put(position, piece);
            }
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String position, int id) {
        Connection connection = getConnection();
        final String sql = "delete from board  where game_id = ? and position = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.domain.piece.InitialPositionPieceGenerator;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.domain.position.Square;

public class PieceDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Connection getConnection() {
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

    public void insertPieces(String gameID) {
        String sql = "insert into piece (position, type, color, gameID) values (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Column column : Column.values()) {
                for (Row row : Row.values()) {
                    statement.setString(1, new Square(column, row).getName());
                    statement.setString(2, InitialPositionPieceGenerator.getType(column, row).name());
                    statement.setString(3, InitialPositionPieceGenerator.getColor(row).name());
                    statement.setString(4, gameID);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPosition(Square target) {
        final String sql = "delete from piece where position = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, target.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(Square source, Square target) {
        String sql = "update piece set position = ? where position = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, target.getName());
            statement.setString(2, source.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(String gameID) {
        String sql = "delete from piece where gameID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

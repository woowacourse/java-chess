package chess.dao;

import chess.domain.piece.Piece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
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

    public Piece getPiece(String location) {
        final Connection connection = getConnection();
        final String sql = "select team, name from board where location = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return PieceConverter.of(resultSet.getString("team"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPiece(String location, Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into board (location, team, name) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location);
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePiece(String location, Piece piece) {
        final Connection connection = getConnection();
        final String sql = "update board set team = ?, name = ? where location = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getTeam());
            statement.setString(2, piece.getName());
            statement.setString(3, location);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        Connection connection = getConnection();
        final String sql = "truncate table board";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

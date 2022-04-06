package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceGenerator;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.domain.position.Square;

public class PieceDao {
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

    public void insertPieces() {
        final Connection connection = getConnection();
        final String sql = "insert into piece (position, type, color) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            for (Column column : Column.values()) {
                for (Row row : Row.values()) {
                    if (!PieceGenerator.generatePiece(column, row).isNone()) {
                        statement.setString(1, new Square(column, row).getName());
                        statement.setString(2, PieceGenerator.getType(column, row).name());
                        statement.setString(3, PieceGenerator.getColor(row).name());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPosition(Square target) {
        final Connection connection = getConnection();
        final String sql = "delete from piece where position = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(Square source, Square target) {
        final Connection connection = getConnection();
        final String sql = "update piece set position = ? where position = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target.getName());
            statement.setString(2, source.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Piece> findAll() {
        final Connection connection = getConnection();
        final String sql = "select * from piece";
        List<Piece> pieces = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                Column column = Column.find(position.charAt(0));
                Row row = Row.find(position.charAt(1));
                pieces.add(PieceGenerator.generatePiece(column, row));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }
}

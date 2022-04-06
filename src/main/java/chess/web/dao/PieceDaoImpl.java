package chess.web.dao;

import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PieceDaoImpl implements PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Connection connection;

    public PieceDaoImpl() {
        connection = getConnection();
    }

    @Override
    public void save(PieceDto pieceDto) {
        final String sql = "insert into piece (piece_type, position, color) values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getPieceType());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String position, PieceDto pieceDto) {
        final String sql = "update piece piece_type=?, position=?, color=? where piece=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getPieceType());
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getColor());
            statement.setString(4, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        final String sql = "delete from piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

package chess.web.dao;

import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<PieceDto> selectAll() {
        final String sql = "select * from piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return toPieceDtos(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<PieceDto> toPieceDtos(ResultSet resultSet) throws SQLException {
        final List<PieceDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(new PieceDto(
                    resultSet.getString("piece_type"),
                    resultSet.getString("position"),
                    resultSet.getString("color")
            ));
        }
        return pieces;
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

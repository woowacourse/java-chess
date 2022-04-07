package chess.dao;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final String name, final String position, final Long gameId) {
        final Connection connection = getConnection();
        final String sql = "insert into board (name, position, gameId) values (?,?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setLong(3, gameId);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(final Long gameId, final Board board) {
        for (Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            save(piece.getName(), position.getPosition(), gameId);
        }
    }

    public Board findById(final Long gameId) {
        Map<Position, Piece> board = new LinkedHashMap<>();
        final Connection connection = getConnection();
        final String sql = "select * from board where gameId = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String name = resultSet.getString("name");
                board.put(Position.create(position), Pieces.find(name));
            }
            connection.close();
            return new Board(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteByGameId(final Long gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from board where gameId = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gameId);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNameByGameIdAndPosition(final Long gameId, final String position, final String name) {
        final Connection connection = getConnection();
        final String sql = "update board set name = ? where gameId = ? and position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setLong(2, gameId);
            statement.setString(3, position);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

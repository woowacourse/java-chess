package chess.dao;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardRepository implements BoardDao {

    @Override
    public void saveAll(final Long gameId, final Board board) {
        for (Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            save(piece.getName(), position.getPosition(), gameId);
        }
    }

    @Override
    public void save(final String name, final String position, final Long gameId) {
        final String sql = "insert into board (name, position, gameId) values (?,?,?)";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setLong(3, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Board findById(final Long gameId) {
        Map<Position, Piece> board = new LinkedHashMap<>();
        final String sql = "select * from board where gameId = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String name = resultSet.getString("name");
                board.put(Position.create(position), Pieces.find(name));
            }
            return new Board(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Board(board);
    }

    @Override
    public void deleteByGameId(final Long gameId) {
        final String sql = "delete from board where gameId = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNameByGameIdAndPosition(final Long gameId, final String position, final String name) {
        final String sql = "update board set name = ? where gameId = ? and position = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setLong(2, gameId);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

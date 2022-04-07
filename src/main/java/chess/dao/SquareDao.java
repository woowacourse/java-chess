package chess.dao;

import chess.entity.Square;
import chess.exception.DeleteQueryException;
import chess.exception.InsertQueryException;
import chess.exception.SelectQueryException;
import chess.exception.UpdateQueryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SquareDao {

    private static final int SQUARE_NUMBER = 64;
    private final Connection connection;

    public SquareDao(Connection connection) {
        this.connection = connection;
    }

    public boolean saveAll(List<Square> squares, long roomId) {
        String sql = "insert into square (position, piece, room_id) values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Square square : squares) {
                statement.setString(1, square.getPosition());
                statement.setString(2, square.getPiece());
                statement.setLong(3, roomId);
                statement.addBatch();
                statement.clearParameters();
            }

            int[] ints = statement.executeBatch();
            if (ints.length == SQUARE_NUMBER) {
                return true;
            }
        } catch (SQLException e) {
            throw new InsertQueryException();
        }
        return false;
    }

    public List<Square> findByRoomId(long roomId) {
        String sql = "select id, position, piece from square where room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            List<Square> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Square(resultSet.getLong("id"),
                        roomId,
                        resultSet.getString("position"),
                        resultSet.getString("piece")));
            }
            return result;
        } catch (SQLException e) {
            throw new SelectQueryException();
        }
    }

    public Optional<Square> findByRoomIdAndPosition(long roomId, String position) {
        String sql = "select id, piece from square where room_id = ? and position = ?";
        Square square = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, roomId);
            statement.setString(2, position);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                square = new Square(resultSet.getLong("id"),
                        roomId,
                        position,
                        resultSet.getString("piece"));
            }
        } catch (SQLException e) {
            throw new SelectQueryException();
        }

        return Optional.ofNullable(square);
    }

    public void update(long roomId, String position, String piece) {
        String sql = "update square set piece = ? where room_id = ? and position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, piece);
            statement.setLong(2, roomId);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateQueryException();
        }
    }

    public void removeAll(long roomId) {
        String sql = "delete from square where room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteQueryException();
        }
    }
}

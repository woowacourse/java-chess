package chess.dao;

import chess.entity.Square;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SquareDao {

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
            if (ints.length == 64) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("sqaure를 저장하지 못했습니다.");
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
            e.printStackTrace();
            throw new IllegalArgumentException(" 입니다.");
        }
    }

    public Square findByRoomIdAndPosition(long roomId, String position) {
        String sql = "select id, piece from square where room_id = ? and position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, roomId);
            statement.setString(2, position);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Square(resultSet.getLong("id"),
                        roomId,
                        position,
                        resultSet.getString("piece"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("존재하지 않는 ID 입니다.");
        }
    }

    public void update(long roomId, String position, String piece) {
        String sql = "update square set piece = ? where room_id = ? and position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, piece);
            statement.setLong(2, roomId);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("존재하지 않는 ID 입니다.");
        }
    }
}

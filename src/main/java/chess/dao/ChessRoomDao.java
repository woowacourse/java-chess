package chess.dao;

import chess.model.room.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChessRoomDao implements RoomDao<Room> {

    private final ConnectionManager connectionManager;

    public ChessRoomDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public List<Room> findAllWithRunning() {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM room "
                    + "JOIN board b "
                    + "ON room.board_id=b.id "
                    + "WHERE b.status='running'";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("board_id"))
                );
            }
            return rooms;
        });
    }

    @Override
    public int deleteAll() {
        return connectionManager.executeQuery(connection -> {
            String sql = "DELETE FROM room";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public Room save(Room room) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "INSERT INTO room (title, board_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, room.getTitle());
            preparedStatement.setInt(2, room.getBoardId());
            preparedStatement.executeUpdate();
            final ChessMemberDao chessMemberDao = new ChessMemberDao(new ConnectionManager());
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("방이 없습니다. 방 제목: " + room.getTitle());
            }
            int roomId = generatedKeys.getInt(1);
            return new Room(
                    roomId,
                    room.getTitle(),
                    room.getBoardId());
        });
    }

    @Override
    public Room getById(int roomId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "SELECT * FROM room WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 방 없습니다. 방 id: " + roomId);
            }
            return new Room(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("board_id"));
        });
    }
}

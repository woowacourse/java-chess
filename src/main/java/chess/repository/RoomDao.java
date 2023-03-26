package chess.repository;

import chess.domain.room.Room;
import java.util.List;

public interface RoomDao {
    void save(final String roomName, final int userId);

    List<Room> findAllByUserId(final int userId);

    Room findById(final int roomId);

    void deleteAll();
}

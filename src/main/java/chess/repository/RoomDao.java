package chess.repository;

import chess.domain.room.Room;
import java.util.List;
import java.util.Optional;

public interface RoomDao {
    void save(final String roomName, final int userId);

    List<Room> findAllByUserId(final int userId);

    Optional<Room> findById(final int roomId);

    void deleteAll();
}

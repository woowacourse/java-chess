package chess.repository;

import chess.domain.room.Room;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    long save(Room room);

    List<Room> findAllByUserId(long userId);

    Optional<Room> findByUserIdAndName(long userId, String name);
}

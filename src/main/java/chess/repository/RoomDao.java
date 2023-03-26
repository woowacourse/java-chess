package chess.repository;

import chess.domain.room.Room;
import java.util.List;

public interface RoomDao {

    long save(Room room);

    List<Room> findAllByUserId(long userId);

    Room findByUserIdAndName(long userId, String name);
}

package chess.repository;

import chess.dto.room.RoomDto;
import java.util.List;

public interface RoomDao {
    void save(final String roomName, final int userId);

    List<RoomDto> findAllByUserId(final int userId);

    RoomDto findById(final int roomId);

    void deleteAll();
}

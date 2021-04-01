package chess.repository.room;

import chess.domain.dto.RoomDto;
import chess.domain.game.Room;
import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {

    long insert(long userId, String name, Room room) throws SQLException;

    void update(long roomId, String name, Room room) throws SQLException;

    RoomDto findRoomByRoomName(String name) throws SQLException;

    boolean isExistRoomName(String name) throws SQLException;

    RoomDto findRoomById(long roomId) throws SQLException;

    List<RoomDto> findRoomsByUserId(long userId) throws SQLException;
}

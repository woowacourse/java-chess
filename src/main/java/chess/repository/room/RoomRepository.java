package chess.repository.room;

import chess.domain.game.Room;
import java.sql.SQLException;

public interface RoomRepository {

    long insert(Room room) throws SQLException;

    void update(Room room) throws SQLException;

    Room findRoomByRoomName(String name) throws SQLException;

    boolean isExistRoomName(String name) throws SQLException;

    Room findRoomById(long roomId) throws SQLException;

    void deleteAll() throws SQLException;

    int count() throws SQLException;
}

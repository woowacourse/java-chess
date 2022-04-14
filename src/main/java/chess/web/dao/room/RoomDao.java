package chess.web.dao.room;

import chess.web.Room;
import java.util.List;

public interface RoomDao {
    void save(String name);

    int findIdByName(String name);

    void removeById(int id);

    Room findById(int id);

    List<Room> findAll();

    void updateNameById(int id, String name);

    void updateRoom(int roomId,
                    int canJoin,
                    String currentCamp);
}

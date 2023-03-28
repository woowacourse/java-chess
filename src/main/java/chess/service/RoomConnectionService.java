package chess.service;

import chess.dao.DBRoomDao;
import chess.domain.Room;

import java.util.List;

public class RoomConnectionService {
    private final DBRoomDao roomDao;

    public RoomConnectionService(DBRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public Room createRoom() {
        int id = roomDao.insert(Room.createEmpty());
        return connectRoom(id);
    }

    public Room connectRoom(final int id) {
        return roomDao.select(id);
    }

    public List<Room> getRooms() {
        return roomDao.selectAll();
    }

}

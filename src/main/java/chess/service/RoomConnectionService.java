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
        Room room = roomDao.select(id);
        if (room == null) {
            throw new IllegalArgumentException("존재하지 않는 게임방입니다.");
        }
        return roomDao.select(id);
    }

    public List<Room> getRooms() {
        return roomDao.selectAll();
    }

}

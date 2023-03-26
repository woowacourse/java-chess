package chess.service;

import chess.domain.room.Room;
import chess.repository.RoomDao;
import java.util.List;

public class RoomService {
    private final RoomDao roomDao;

    public RoomService(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void save(final String roomName, final int userId) {
        roomDao.save(roomName, userId);
    }

    public List<Room> findAllByUserId(final int userId) {
        return roomDao.findAllByUserId(userId);
    }

    public Room findById(final int id, final int userId) {
        final Room room = roomDao.findById(id);
        if (room == null) {
            throw new IllegalArgumentException("아이디에 해당하는 방이 없습니다.");
        }
        if (room.isNotCreatedBy(userId)) {
            throw new IllegalArgumentException("방의 주인이 아닙니다.");
        }
        return room;
    }
}

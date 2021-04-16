package chess.service;

import chess.controller.web.dto.RoomDto;
import chess.dao.RoomDao;

import java.util.List;

public class RoomService {
    private final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void create(String roomName) {
        roomDao.insert(roomName);
    }

    public void delete(Long roomId) {
        roomDao.delete(roomId);
    }

    public List<RoomDto> load() {
        return roomDao.selectAll();
    }
}

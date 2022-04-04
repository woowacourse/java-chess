package chess.service;

import chess.dao.RoomDao;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.RoomStatusDto;

public class RoomService {

    public boolean isRoomExist(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        return roomDao.isExistName(roomName);
    }

    public void createRoom(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        roomDao.save(roomName, GameStatus.READY, Color.WHITE);
    }

    public void deleteRoom(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomStatusDto dto = roomDao.findStatusByName(roomName);
        if (dto.getGameStatus().isEnd()) {
            roomDao.delete(roomName);
        }
    }
}

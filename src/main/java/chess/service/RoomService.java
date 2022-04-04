package chess.service;

import chess.dao.RoomDao;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.RoomDto;
import java.util.Objects;

public class RoomService {

    public boolean isRoomExist(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto dto = roomDao.findByName(roomName);
        return Objects.nonNull(dto);
    }

    public void createRoom(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        roomDao.save(roomName, GameStatus.READY, Color.WHITE);
    }

    public void deleteRoom(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto dto = roomDao.findByName(roomName);
        if (dto.getGameStatus().isEnd()) {
            roomDao.delete(roomName);
        }
    }
}

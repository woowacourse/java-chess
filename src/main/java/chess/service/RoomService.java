package chess.service;

import chess.dao.RoomDao;
import chess.dao.StatusDao;
import chess.dto.RoomDto;
import chess.dto.StatusDto;
import java.util.Objects;

public class RoomService {

    public boolean isRoomExist(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto dto = roomDao.findByName(roomName);
        return Objects.nonNull(dto);
    }

    public void createRoom(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        roomDao.save(roomName);
    }

    public void deleteRoom(final String roomName) {
        final StatusDao statusDao = new StatusDao();
        final StatusDto statusDto = statusDao.findByRoomName(roomName);
        if (statusDto.getGameStatus().isEnd()) {
            final RoomDao roomDao = new RoomDao();
            roomDao.delete(roomName);
        }
    }
}

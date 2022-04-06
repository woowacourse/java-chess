package chess.service;

import chess.dao.RoomDao;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.CurrentTurnDto;
import chess.dto.RoomStatusDto;
import chess.view.JsonGenerator;

public class RoomService {

    private final RoomDao roomDao;

    public RoomService() {
        this.roomDao = new RoomDao();
    }

    public boolean isRoomExist(final String roomName) {
        return roomDao.isExistName(roomName);
    }

    public void createRoom(final String roomName) {
        roomDao.save(roomName, GameStatus.READY, Color.WHITE);
    }

    public void deleteRoom(final String roomName) {
        final RoomStatusDto dto = roomDao.findStatusByName(roomName);
        if (dto.getGameStatus().isEnd()) {
            roomDao.delete(roomName);
        }
    }

    public JsonGenerator findCurrentTurn(final String roomName) {
        final JsonGenerator result = JsonGenerator.create();
        try {
            final RoomDao roomDao = new RoomDao();
            final CurrentTurnDto dto = roomDao.findCurrentTurnByName(roomName);
            result.addCurrentTurn(dto.getCurrentTurn());
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }
}

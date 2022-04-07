package chess.service;

import chess.StatusCode;
import chess.dao.RoomDao;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.CurrentTurnDto;
import chess.dto.ErrorResponseDto;
import chess.dto.RoomStatusDto;
import com.google.gson.Gson;

public class RoomService {
    private final RoomDao roomDao;

    private final Gson gson;

    public RoomService() {
        this.roomDao = new RoomDao();
        this.gson = new Gson();
    }

    public boolean isExistRoom(final String roomName) {
        return roomDao.isExistName(roomName);
    }

    public String createRoom(final String roomName) {
        roomDao.save(roomName, GameStatus.READY, Color.WHITE);
        return null;
    }

    public String deleteRoom(final String roomName) {
        try {
            checkRoomExist(roomName);

            final RoomStatusDto dto = roomDao.findStatusByName(roomName);
            if (dto.getGameStatus().isEnd()) {
                roomDao.delete(roomName);
            }

            return null;
        } catch (IllegalArgumentException e) {
            final ErrorResponseDto dto = ErrorResponseDto.of(e, StatusCode.BAD_REQUEST);
            return gson.toJson(dto);
        }
    }

    public String findCurrentTurn(final String roomName) {
        try {
            checkRoomExist(roomName);
            final CurrentTurnDto dto = roomDao.findCurrentTurnByName(roomName);
            return gson.toJson(dto);
        } catch (IllegalArgumentException e) {
            final ErrorResponseDto dto = ErrorResponseDto.of(e, StatusCode.BAD_REQUEST);
            return gson.toJson(dto);
        }
    }

    private void checkRoomExist(final String roomName) {
        if (!roomDao.isExistName(roomName)) {
            throw new IllegalArgumentException("존재하지 않는 방 입니다.");
        }
    }
}

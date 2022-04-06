package chess.service;

import chess.dao.RoomDao;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.CurrentTurnDto;
import chess.dto.RoomStatusDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;

public class RoomService {
    private final RoomDao roomDao;

    private final Gson gson;

    public RoomService() {
        this.roomDao = new RoomDao();
        this.gson = new Gson();
    }

    public boolean isRoomExist(final String roomName) {
        return roomDao.isExistName(roomName);
    }

    public String createRoom(final Request req, final Response res) {
        final String roomName = req.params(":name");
        roomDao.save(roomName, GameStatus.READY, Color.WHITE);
        return null;
    }

    public String deleteRoom(final Request req, final Response res) {
        try {
            final String roomName = req.params(":name");
            checkRoomExist(roomName);
            final RoomStatusDto dto = roomDao.findStatusByName(roomName);
            if (dto.getGameStatus().isEnd()) {
                roomDao.delete(roomName);
            }
            return null;
        } catch (IllegalArgumentException e) {
            res.status(404);
            final Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            return gson.toJson(map);
        }
    }

    public String findCurrentTurn(final Request req, final Response res) {
        try {
            final String roomName = req.params(":name");
            checkRoomExist(roomName);
            final CurrentTurnDto dto = roomDao.findCurrentTurnByName(roomName);
            return gson.toJson(dto);
        } catch (IllegalArgumentException e) {
            res.status(404);
            final Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            return gson.toJson(map);
        }
    }

    private void checkRoomExist(final String roomName) {
        if (!roomDao.isExistName(roomName)) {
            throw new IllegalArgumentException("존재하지 않는 방 입니다.");
        }
    }
}

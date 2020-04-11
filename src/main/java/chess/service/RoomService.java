package chess.service;

import chess.dto.RoomDto;
import chess.repository.CachedRoomRepository;
import chess.repository.RoomRepository;
import chess.result.Result;

import java.sql.SQLException;

public class RoomService {
    private static final int DEFAULT_VALUE = -1;

    private RoomRepository roomRepository = new CachedRoomRepository();

    public Result create(RoomDto roomDto) {
        try {
            return roomRepository.create(roomDto);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    public Result status(int roomId) {
        try {
            return roomRepository.findById(roomId);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    public Result join(String roomName, int userId) {
        Result result;
        try {
            result = roomRepository.findByName(roomName);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }

        if (!result.isSuccess()) {
            return new Result(false, "Can not find room. Room name : " + roomName);
        }
        RoomDto roomDto = (RoomDto) result.getObject();

        if (roomDto.getWhiteUserId() == DEFAULT_VALUE) {
            roomDto.setWhiteUserId(userId);
        } else if (roomDto.getBlackUserId() == DEFAULT_VALUE) {
            roomDto.setBlackUserId(userId);
        } else {
            return new Result(false, "Room is full");
        }

        try {
            roomRepository.update(roomDto);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }

        return new Result(true, roomDto.getRoomId());
    }

    public Result exit(int roomId, int userId) {
        Result result;
        try {
            result = roomRepository.findById(roomId);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }

        if (!result.isSuccess()) {
            return new Result(false, "Can not find room. Room id : " + roomId);
        }
        RoomDto roomDto = (RoomDto) result.getObject();

        if (roomDto.getWhiteUserId() == userId) {
            roomDto.setWhiteUserId(DEFAULT_VALUE);
        } else if (roomDto.getBlackUserId() == userId) {
            roomDto.setBlackUserId(DEFAULT_VALUE);
        } else {
            return new Result(false, "id : " + userId + "is not in this room.");
        }

        try {
            return roomRepository.update(roomDto);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    public Result quit(int roomId) {
        Result result;
        try {
            result = roomRepository.findById(roomId);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }

        if (!result.isSuccess()) {
            return new Result(false, "Can not find room. Room id : " + roomId);
        }

        try {
            return roomRepository.delete(roomId);
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }
}

package chess.repository;

import chess.dto.RoomDto;
import chess.result.Result;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CachedRoomRepository implements RoomRepository {
    private static RoomRepository roomRepository = new RoomRepositoryImpl();
    private static Map<Integer, RoomDto> cachedRoom = new HashMap<>();

    @Override
    public Result create(RoomDto roomDto) throws SQLException {
        return roomRepository.create(roomDto);
    }

    @Override
    public Result findById(int roomId) throws SQLException {
        if (cachedRoom.containsKey(roomId)) {
            return new Result(true, cachedRoom.get(roomId));
        }
        Result result = roomRepository.findById(roomId);
        if (result.isSuccess()) {
            RoomDto roomDto = (RoomDto) result.getObject();
            cachedRoom.put(roomId, roomDto);
            return new Result(true, roomDto);
        }
        return new Result(false, null);
    }

    @Override
    public Result findByName(final String roomName) throws SQLException {
        Result result = roomRepository.findByName(roomName);
        if (result.isSuccess()) {
            RoomDto roomDto = (RoomDto) result.getObject();
            cachedRoom.put(roomDto.getRoomId(), roomDto);
            return new Result(true, roomDto);
        }
        return new Result(false, null);
    }


    @Override
    public Result update(final RoomDto roomDto) throws SQLException {
        Result result = roomRepository.update(roomDto);
        if (result.isSuccess()) {
            cachedRoom.put(roomDto.getRoomId(), roomDto);
        }
        return result;
    }

    @Override
    public Result delete(final int roomId) throws SQLException {
        Result result = roomRepository.delete(roomId);
        if (result.isSuccess()) {
            cachedRoom.remove(roomId);
        }
        return result;
    }

    @Override
    public Result deleteAll() throws SQLException {
        return roomRepository.deleteAll();
    }
}

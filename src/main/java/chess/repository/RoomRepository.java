package chess.repository;

import chess.dto.RoomDto;
import chess.result.Result;

import java.sql.SQLException;

public interface RoomRepository {
    Result create(RoomDto roomDto) throws SQLException;

    Result findById(int roomId) throws SQLException;

    Result findByName(String roomName) throws SQLException;

    Result update(RoomDto roomDto) throws SQLException;

    Result delete(int roomId) throws SQLException;

    Result deleteAll() throws SQLException;
}

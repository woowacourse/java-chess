package chess.dao;

import chess.domain.Room;

import java.util.List;

public interface RoomDAO {
    void create(String roomReqData, int gameId);
    Room readRoomFromId(String roomId);
    List<Room> readTotalRoom();
}

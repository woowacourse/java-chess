package chess.service;

import chess.domain.room.Room;
import chess.repository.RoomRepository;

import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAllRooms() throws SQLException {
        return roomRepository.findAllRooms();
    }

    public void insertRoom(String roomName) throws SQLException {
        roomRepository.insertRoom(roomName);
    }

    public Room findLatestRoom() throws SQLException {
        return roomRepository.findLatestRoom()
                .orElseThrow(() -> new IllegalStateException("Room이 없습니다."));
    }
}

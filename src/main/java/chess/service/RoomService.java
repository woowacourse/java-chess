package chess.service;

import chess.domain.room.Room;
import chess.dto.RoomDTO;
import chess.repository.RoomRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> findAllRooms() throws SQLException {
        return roomRepository.findAllRooms()
                .stream()
                .map(RoomDTO::from)
                .collect(Collectors.toList());
    }

    public void insertRoom(String roomName) throws SQLException {
        roomRepository.insertRoom(roomName);
    }

    public RoomDTO findLatestRoom() throws SQLException {
        Room room = roomRepository.findLatestRoom()
                .orElseThrow(() -> new IllegalStateException("Room이 없습니다."));
        return RoomDTO.from(room);
    }
}

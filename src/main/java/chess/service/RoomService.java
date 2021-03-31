package chess.service;

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

    public List<RoomDTO> updateRooms() throws SQLException {
        return roomRepository.findAllRooms()
                .stream()
                .map(RoomDTO::from)
                .collect(Collectors.toList());
    }
}

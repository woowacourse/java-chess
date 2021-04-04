package chess.service;

import chess.dao.RoomDAO;
import chess.dto.RoomDTO;

import java.util.List;

public final class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(final RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<RoomDTO> allRooms() throws Exception {
        return roomDAO.allRooms();
    }

    public void createRoom(final String name) throws Exception {
        roomDAO.createRoom(name);
    }

    public void changeStatus(final String roomId) throws Exception {
        roomDAO.changeStatusEndByRoomId(roomId);
    }

    public List<String> allRoomsId() throws Exception {
        return roomDAO.allRoomIds();
    }
}

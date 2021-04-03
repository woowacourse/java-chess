package chess.service;

import chess.dao.RoomDAO;
import chess.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(final RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<RoomDTO> allRooms() throws SQLException {
        return roomDAO.allRooms();
    }

    public void createRoom(final String name) throws SQLException {
        roomDAO.createRoom(name);
    }

    public void changeStatus(final String roomId) throws SQLException {
        roomDAO.changeStatusEndByRoomId(roomId);
    }
}

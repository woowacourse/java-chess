package chess.model.service;

import chess.model.dto.CreateRoomDto;
import chess.model.dto.DeleteRoomDto;
import chess.model.dto.RoomDto;
import chess.model.repository.RoomDao;
import java.sql.SQLException;

public class RoomService {

    private static final RoomDao ROOM_DAO = RoomDao.getInstance();
    private static final RoomService INSTANCE = new RoomService();

    private RoomService() {
    }

    public static RoomService getInstance() {
        return INSTANCE;
    }

    public RoomDto getUsedRooms() throws SQLException {
        return new RoomDto(ROOM_DAO.selectUsedOnly());
    }

    public void addRoom(CreateRoomDto createRoomDto) throws SQLException {
        ROOM_DAO.insert(createRoomDto.getRoomName(), createRoomDto.getRoomPassword());
    }

    public void deleteRoom(DeleteRoomDto deleteRoomDto) throws SQLException {
        ROOM_DAO.updateUsedN(deleteRoomDto.getRoomId());
    }
}

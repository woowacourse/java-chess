package service;

import dao.RoomDao;
import dto.RoomDto;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
	public static final RoomDao ROOM_DAO = new RoomDao();

	public List<RoomDto> findAllRooms() throws SQLException {
		return ROOM_DAO.findAllRooms();
	}
}

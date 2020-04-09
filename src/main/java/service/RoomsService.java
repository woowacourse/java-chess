package service;

import dao.RoomDao;
import dto.RoomDto;

import java.sql.SQLException;
import java.util.List;

public class RoomsService {
	public static final RoomDao ROOM_DAO = new RoomDao();

	public List<RoomDto> findAllRooms() throws SQLException {
		return ROOM_DAO.findAllRooms();
	}

	public RoomDto findRoomByRoomName(final String roomName) throws SQLException {
		return ROOM_DAO.findRoomByRoomName(roomName);
	}

	public int addRoomByRoomName(final String roomName) throws SQLException {
		return ROOM_DAO.addRoomByRoomName(roomName);
	}

	public int deleteRoomByRoomName(final String roomName) throws SQLException {
		return ROOM_DAO.deleteRoomByRoomName(roomName);
	}
}

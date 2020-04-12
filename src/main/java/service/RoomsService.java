package service;

import dao.RoomDao;
import dto.RoomDto;

import java.sql.SQLException;
import java.util.List;

public class RoomsService {
	private static final RoomsService roomsService;

	static {
		roomsService = new RoomsService(new RoomDao());
	}

	public final RoomDao roomDao;

	private RoomsService(final RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public static RoomsService getInstance() {
		return roomsService;
	}

	public List<RoomDto> findAllRooms() throws SQLException {
		return roomDao.findAllRooms();
	}

	public RoomDto findRoomByRoomName(final String roomName) throws SQLException {
		return roomDao.findRoomByRoomName(roomName);
	}

	public int addRoomByRoomName(final String roomName) throws SQLException {
		return roomDao.addRoomByRoomName(roomName);
	}

	public int deleteRoomByRoomName(final String roomName) throws SQLException {
		return roomDao.deleteRoomByRoomName(roomName);
	}
}

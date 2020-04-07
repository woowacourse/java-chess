package chess.service;

import java.sql.SQLException;
import java.util.List;

import chess.dao.RoomDAO;
import chess.domain.room.Room;

public class RoomService {
	private static final RoomService ROOM_SERVICE = new RoomService();

	public static RoomService getInstance() {
		return ROOM_SERVICE;
	}

	public void addRoom(String roomName) throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		roomDAO.addRoom(roomName, "white");
	}

	public void removeRoom(String roomId) throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		roomDAO.removeRoomById(Integer.parseInt(roomId));
	}

	public Room findRoom(String roomName) throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		int roomId = roomDAO.findRoomIdByRoomName(roomName);
		return roomDAO.findRoomById(roomId);
	}

	public List<Room> findAllRoom() throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		List<Room> rooms = roomDAO.findAllRoom();
		return rooms;
	}
}

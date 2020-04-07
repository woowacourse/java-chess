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
		roomDAO.addRoom(roomName, "WHITE");
	}

	public void removeRoom(int roomId) throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		roomDAO.removeRoomById(roomId);
	}

	public Room findRoom(int roomId) throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		return roomDAO.findRoomById(roomId);
	}

	public List<Room> findAllRoom() throws SQLException {
		RoomDAO roomDAO = RoomDAO.getInstance();
		List<Room> rooms = roomDAO.findAllRoom();
		return rooms;
	}
}

package dao;

public class Room {
	private final int id;
	private final String roomName;

	public Room(final int id, final String roomName) {
		this.id = id;
		this.roomName = roomName;
	}

	public int getId() {
		return id;
	}

	public String getRoomName() {
		return roomName;
	}
}
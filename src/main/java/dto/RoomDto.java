package dto;

public class RoomDto {
	private final int id;
	private final String roomName;

	public RoomDto(final int id, final String roomName) {
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
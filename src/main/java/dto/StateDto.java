package dto;

public class StateDto {
	final int id;
	final String state;
	final int roomId;

	public StateDto(final int id, final String state, final int roomId) {
		this.id = id;
		this.state = state;
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public int getRoomId() {
		return roomId;
	}
}

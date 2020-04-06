package dao;

public class State {
	final int id;
	final String state;
	final int roomId;

	public State(final int id, final String state, final int roomId) {
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

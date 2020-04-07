package chess.domain.room;

public class Room {
	private final int roomId;
	private final String roomName;
	private final String turn;

	public Room(int roomId, String roomName, String turn) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.turn = turn;
	}

	public int getRoomId() {
		return roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getTurn() {
		return turn;
	}
}

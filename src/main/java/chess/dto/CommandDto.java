package chess.dto;

public class CommandDto {
	private long round;
	private long roomId;
	private String origin;
	private String target;

	public void setTarget(final String target) {
		this.target = target;
	}

	public void setOrigin(final String origin) {
		this.origin = origin;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRound(final long round) {
		this.round = round;
	}

	public long getRound() {
		return round;
	}

	public void setRoomId(final long room_id) {
		this.roomId = room_id;
	}

	public String getOrigin() {
		return origin;
	}

	public String getTarget() {
		return target;
	}
}

package chess.dto;

public class CommandDto {
	private long round;
	private long room_id;
	private String origin;
	private String target;

	public void setTarget(final String target) {
		this.target = target;
	}

	public void setOrigin(final String origin) {
		this.origin = origin;
	}

	public void setRoomId(final long room_id) {
		this.room_id = room_id;
	}

	public void setRound(final long round) {
		this.round = round;
	}

	public long getRound() {
		return round;
	}

	public long getRoom_id() {
		return room_id;
	}

	public String getOrigin() {
		return origin;
	}

	public String getTarget() {
		return target;
	}
}

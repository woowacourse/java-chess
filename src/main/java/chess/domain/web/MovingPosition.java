package chess.domain.web;

public class MovingPosition {
	private final String start;
	private final String end;

	public MovingPosition(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}

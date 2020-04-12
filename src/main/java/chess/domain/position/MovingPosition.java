package chess.domain.position;

public class MovingPosition {
	private final String start;
	private final String end;

	public MovingPosition(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public boolean isStartAndEndSame() {
		return start.equals(end);
	}

	public Position getStartPosition() {
		return PositionFactory.of(start);
	}

	public Position getEndPosition() {
		return PositionFactory.of(end);
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}

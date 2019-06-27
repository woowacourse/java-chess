package chess.domain;

public class MovementInfo {
	private final Direction direction;
	private final int maxDistance;

	public MovementInfo(final Direction direction, final int maxDistance) {
		this.direction = direction;
		this.maxDistance = maxDistance;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getMaxDistance() {
		return maxDistance;
	}
}

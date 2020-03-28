package chess.domain.piece;

public class RelativePosition {
	private final int x;
	private final int y;

	private RelativePosition(Position source, Position target) {
		this.x = target.getX() - source.getX();
		this.y = target.getY() - source.getY();
	}

	public static RelativePosition of(Position source, Position target) {
		return new RelativePosition(source, target);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

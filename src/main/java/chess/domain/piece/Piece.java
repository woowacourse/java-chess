package chess.domain.piece;

public abstract class Piece {
	protected Position position;
	private Color color;

	public Piece(Position position, Color color) {
		this.position = position;
		this.color = color;
	}

	public boolean isBlack() {
		return color.isBlack();
	}

	public abstract void moveTo(Position position);
}

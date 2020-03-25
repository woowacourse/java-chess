package chess.domain.piece;

public abstract class Piece {
	private Position position;

	public Piece(Position position) {
		this.position = position;
	}

	public abstract void moveTo(Position position);
}

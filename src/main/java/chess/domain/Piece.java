package chess.domain;

public abstract class Piece {
	protected final Position position;

	public Piece(Position position) {
		this.position = position;
	}

	public abstract void move(Position source, Position destination);

	@Override
	public String toString() {
		return "*";
	}
}

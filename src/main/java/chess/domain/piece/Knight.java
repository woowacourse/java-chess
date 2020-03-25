package chess.domain.piece;

public class Knight extends Piece {
	public Knight(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

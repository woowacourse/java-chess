package chess.domain.piece;

public class Queen extends Piece {
	public Queen(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

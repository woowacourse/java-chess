package chess.domain.piece;

public class King extends Piece {
	public King(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

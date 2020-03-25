package chess.domain.piece;

public class Bishop extends Piece {
	public Bishop(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

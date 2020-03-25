package chess.domain.piece;

public class Pawn extends Piece {
	public Pawn(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

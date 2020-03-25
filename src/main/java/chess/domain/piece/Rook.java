package chess.domain.piece;

public class Rook extends Piece {
	public Rook(Position position, Color color) {
		super(position, color);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}

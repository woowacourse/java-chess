package chess.domain.piece;

public class Blank extends Piece {
	public Blank(Position position) {
		super(position, Color.NONE);
	}

	@Override
	public void moveTo(Position position) {
		throw new UnsupportedOperationException();
	}
}

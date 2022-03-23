package chess.domain.piece;

public class Blank extends Piece {

	public Blank() {
		super(Team.NEUTRALITY);
	}

	@Override
	protected String createSymbol(final Team team) {
		return ".";
	}

	@Override
	public boolean isBlank() {
		return true;
	}
}

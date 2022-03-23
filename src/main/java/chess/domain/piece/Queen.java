package chess.domain.piece;

public class Queen extends Piece {

	public Queen(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "Q";
		}
		return "q";
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}

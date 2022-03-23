package chess.domain.piece;

public class Pawn extends Piece {

	public Pawn(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "P";
		}
		return "p";
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}

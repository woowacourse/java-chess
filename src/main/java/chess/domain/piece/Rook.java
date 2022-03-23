package chess.domain.piece;

public class Rook extends Piece {

	public Rook(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "R";
		}
		return "r";
	}

	@Override
	public boolean isBlank() {
		return false;
	}

}

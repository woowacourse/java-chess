package chess.domain.piece;

public class Knight extends Piece {

	public Knight(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "N";
		}
		return "n";
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}
